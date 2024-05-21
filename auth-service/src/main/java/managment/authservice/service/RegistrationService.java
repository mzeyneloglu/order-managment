package managment.authservice.service;

import lombok.RequiredArgsConstructor;
import managment.authservice.config.security.JWTGenerator;
import managment.authservice.controller.request.LoginRequest;
import managment.authservice.controller.request.RegistrationRequest;
import managment.authservice.controller.response.AuthResponse;
import managment.authservice.domain.AppUser;
import managment.authservice.domain.Role;
import managment.authservice.repository.RoleRepository;
import managment.authservice.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;
    private final RestTemplate restTemplate;
    public ResponseEntity<Long> register(RegistrationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode((request.getPassword())));
        user.setAddress(request.getAddress());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setPhone(request.getPhone());

        Role roles = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        String customerServiceURL = "http://localhost:9191/customer-service/api/customer/create";

        JSONObject customerServiceRequest = getCustomerRequestJsonObject(request);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(customerServiceRequest.toString(), headers);

        ResponseEntity<Long> customerId = restTemplate.exchange(customerServiceURL, HttpMethod.POST, httpEntity, Long.class);

        return new ResponseEntity<>(customerId.getBody(), HttpStatus.OK);
    }

    private static JSONObject getCustomerRequestJsonObject(RegistrationRequest request) {
        JSONObject customerServiceRequest = new JSONObject();
        customerServiceRequest.put("customerName", request.getName());
        customerServiceRequest.put("customerSurname", request.getSurname());
        customerServiceRequest.put("customerEmail", request.getEmail());
        customerServiceRequest.put("customerPhoneNumber", request.getPhone());
        customerServiceRequest.put("customerAddress", request.getAddress());
        customerServiceRequest.put("customerUsername", request.getUsername());
        return customerServiceRequest;
    }

    public ResponseEntity<AuthResponse> login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);


        String customerServiceURL = "http://localhost:9191/customer-service/api/customer/get-id-by-username/";
        Long customerId = restTemplate.getForObject(customerServiceURL + request.getUsername(), Long.class);
        return new ResponseEntity<>(new AuthResponse(token, customerId), HttpStatus.OK);

    }
}

