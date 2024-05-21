package managment.customerservice.service.impl;

import lombok.RequiredArgsConstructor;
import managment.customerservice.controller.request.CreateCustomerRequest;
import managment.customerservice.controller.request.UpdateCustomerRequest;
import managment.customerservice.controller.response.ProductClientProductResponse;
import static org.apache.commons.lang.StringUtils.*;
import managment.customerservice.exception.BusinessLogicConstants;
import managment.customerservice.exception.BusinessLogicException;
import managment.customerservice.model.Customer;
import managment.customerservice.model.dto.CustomerDTO;
import managment.customerservice.repository.CustomerRepository;
import managment.customerservice.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Override
    public Long create(CreateCustomerRequest createCustomerRequest) {
        if (isEmpty(createCustomerRequest.getCustomerAddress())
                || isEmpty(createCustomerRequest.getCustomerEmail())
                || isEmpty(createCustomerRequest.getCustomerName())
                || isEmpty(createCustomerRequest.getCustomerPhoneNumber())
                || isEmpty(createCustomerRequest.getCustomerSurname())
                || isEmpty(createCustomerRequest.getCustomerUsername())) {
            throw new BusinessLogicException(BusinessLogicConstants.PR1001);
        }

        Customer customer = new Customer();
        customer.setName(createCustomerRequest.getCustomerName());
        customer.setSurname(createCustomerRequest.getCustomerSurname());
        customer.setEmail(createCustomerRequest.getCustomerEmail());
        customer.setPhone(createCustomerRequest.getCustomerPhoneNumber());
        customer.setAddress(createCustomerRequest.getCustomerAddress());
        customer.setUsername(createCustomerRequest.getCustomerUsername());

        customerRepository.save(customer);
        return customer.getId();

    }
    @Override
    public void createMany(List<CreateCustomerRequest> createCustomerRequestList) {
        if (ObjectUtils.isEmpty(createCustomerRequestList)) {
            throw new BusinessLogicException(BusinessLogicConstants.PR1001);
        }
        customerRepository.saveAll(createCustomerRequestList.stream().map(request -> {
            Customer customer = new Customer();
            customer.setName(request.getCustomerName());
            customer.setSurname(request.getCustomerSurname());
            customer.setEmail(request.getCustomerEmail());
            customer.setPhone(request.getCustomerPhoneNumber());
            customer.setAddress(request.getCustomerAddress());
            customer.setUsername(request.getCustomerUsername());
            return customer;
        }).collect(Collectors.toList()));
    }

    @Override
    public ProductClientProductResponse getProduct(Long productId) {
        if (productId < 0 || ObjectUtils.isEmpty(productId)) {
            throw new BusinessLogicException(BusinessLogicConstants.PR1003);
        }
        return restTemplate.getForObject("http://localhost:9191/product-service/api/product/get-product/" + productId, ProductClientProductResponse.class);
    }

    @Override
    public Long getByUsername(String username) {
        if(isEmpty(username)) {
            throw new BusinessLogicException(BusinessLogicConstants.PR1001);
        }
        return customerRepository.getCustomerIdByCustomerUsername(username);
    }

    @Override
    public void updateExternal(UpdateCustomerRequest updateCustomerRequest) {
        if (isEmpty(updateCustomerRequest.getCustomerDto().getCustomerName()) ||
            isEmpty(updateCustomerRequest.getCustomerDto().getCustomerSurname()) ||
            isEmpty(updateCustomerRequest.getCustomerDto().getCustomerEmail()) ||
            isEmpty(updateCustomerRequest.getCustomerDto().getCustomerPhone()) ||
            isEmpty(updateCustomerRequest.getCustomerDto().getCustomerAddress())) {

            throw new BusinessLogicException(BusinessLogicConstants.PR1001);
        }
        Customer customer = customerRepository.findCustomerById(updateCustomerRequest.getCustomerDto().getCustomerId());
        if (ObjectUtils.isEmpty(customer)) {
            throw new BusinessLogicException(BusinessLogicConstants.PR1002);
        }
        customer.setName(updateCustomerRequest.getCustomerDto().getCustomerName());
        customer.setSurname(updateCustomerRequest.getCustomerDto().getCustomerSurname());
        customer.setEmail(updateCustomerRequest.getCustomerDto().getCustomerEmail());
        customer.setPhone(updateCustomerRequest.getCustomerDto().getCustomerPhone());
        customer.setAddress(updateCustomerRequest.getCustomerDto().getCustomerAddress());
        customerRepository.save(customer);

    }
    @Override
    public CustomerDTO get(Long customerId) {
        if (ObjectUtils.isEmpty(customerId)) {
            throw new BusinessLogicException(BusinessLogicConstants.PR1002);
        }
        Customer customer = customerRepository.findById(customerId).orElse(null);
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
            throw new BusinessLogicException(BusinessLogicConstants.PR1002);
        }
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        customer.setName(updateCustomerRequest.getCustomerDto().getCustomerName());
        customer.setSurname(updateCustomerRequest.getCustomerDto().getCustomerSurname());
        customer.setEmail(updateCustomerRequest.getCustomerDto().getCustomerEmail());
        customer.setPhone(updateCustomerRequest.getCustomerDto().getCustomerPhone());
        customer.setAddress(updateCustomerRequest.getCustomerDto().getCustomerAddress());
        customer.setUsername(updateCustomerRequest.getCustomerDto().getCustomerUsername());

        customerRepository.save(customer);

    }

    @Override
    public void delete(Long customerId) {
        if (ObjectUtils.isEmpty(customerId)) {
            throw new BusinessLogicException(BusinessLogicConstants.PR1002);
        }
        customerRepository.deleteById(customerId);

    }
}
