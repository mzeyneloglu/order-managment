package managment.customerservice.service.impl;

import lombok.RequiredArgsConstructor;
import managment.customerservice.controller.request.CreateCustomerRequest;
import managment.customerservice.controller.request.UpdateCustomerRequest;
import managment.customerservice.controller.response.ProductClientProductResponse;
import managment.customerservice.exception.BusinessLogicException;
import managment.customerservice.model.Customer;
import managment.customerservice.model.CustomerDTO;
import managment.customerservice.model.dto.ProductDTO;
import managment.customerservice.repository.CustomerRepository;
import managment.customerservice.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    @Override
    public void create(CreateCustomerRequest createCustomerRequest) {
        if (createCustomerRequest == null) {
            throw new BusinessLogicException("Request cannot be null");
        }
        Customer customer = new Customer();
        customer.setName(createCustomerRequest.getCustomerName());
        customer.setSurname(createCustomerRequest.getCustomerSurname());
        customer.setEmail(createCustomerRequest.getCustomerEmail());
        customer.setPhone(createCustomerRequest.getCustomerPhoneNumber());
        customer.setAddress(createCustomerRequest.getCustomerAddress());

        customerRepository.save(customer);

    }
    @Override
    public void createMany(List<CreateCustomerRequest> createCustomerRequestList) {
        if (ObjectUtils.isEmpty(createCustomerRequestList)) {
            throw new BusinessLogicException("Cannot be null");
        }
        customerRepository.saveAll(createCustomerRequestList.stream().map(request -> {
            Customer customer = new Customer();
            customer.setName(request.getCustomerName());
            customer.setSurname(request.getCustomerSurname());
            customer.setEmail(request.getCustomerEmail());
            customer.setPhone(request.getCustomerPhoneNumber());
            customer.setAddress(request.getCustomerAddress());
            return customer;
        }).collect(Collectors.toList()));
    }

    @Override
    public ProductClientProductResponse getProduct(Long productId) {
        if (productId < 0 || ObjectUtils.isEmpty(productId)) {
            throw new BusinessLogicException("Bad request");
        }
        return restTemplate.getForObject("http://localhost:8181/api/product/get-product/" + productId, ProductClientProductResponse.class);
    }

    @Override
    public CustomerDTO get(Long customerId) {
        if (ObjectUtils.isEmpty(customerId)) {
            throw new BusinessLogicException("Cannot be null");
        }
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        return new CustomerDTO(customer);
    }

    @Override
    public List<CustomerDTO> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void update(UpdateCustomerRequest updateCustomerRequest, Long customerId) {
        if (ObjectUtils.isEmpty(updateCustomerRequest) || ObjectUtils.isEmpty(customerId)) {
            throw new BusinessLogicException("Cannot be null request or id");
        }
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        customer.setName(updateCustomerRequest.getCustomerDto().getCustomerName());
        customer.setSurname(updateCustomerRequest.getCustomerDto().getCustomerSurname());
        customer.setEmail(updateCustomerRequest.getCustomerDto().getCustomerEmail());
        customer.setPhone(updateCustomerRequest.getCustomerDto().getCustomerPhone());
        customer.setAddress(updateCustomerRequest.getCustomerDto().getCustomerAddress());

        customerRepository.save(customer);

    }

    @Override
    public void delete(Long customerId) {
        if (ObjectUtils.isEmpty(customerId)) {
            throw new BusinessLogicException("Cannot be null");
        }
        customerRepository.deleteById(customerId);

    }
}
