package managment.customerservice.service;

import managment.customerservice.controller.request.CreateCustomerRequest;
import managment.customerservice.controller.request.UpdateCustomerRequest;
import managment.customerservice.model.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerService {
    void create(CreateCustomerRequest createCustomerRequest);

    CustomerDTO get(Long customerId);

    List<CustomerDTO> getAll();

    void update(UpdateCustomerRequest updateCustomerRequest, Long customerId);

    void delete(Long customerId);
    void createMany(List<CreateCustomerRequest> createCustomerRequestList);
}
