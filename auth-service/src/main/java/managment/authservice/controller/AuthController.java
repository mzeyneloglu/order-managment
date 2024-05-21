package managment.authservice.controller;

import lombok.RequiredArgsConstructor;
import managment.authservice.constants.ApiEndpoints;
import managment.authservice.controller.request.LoginRequest;
import managment.authservice.controller.request.RegistrationRequest;
import managment.authservice.controller.response.AuthResponse;
import managment.authservice.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiEndpoints.END_POINT)
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return registrationService.login(request);
    }


}
