package managment.customerservice.service;

import managment.customerservice.controller.request.CreateCustomerRequest;
import managment.customerservice.controller.request.UpdateCustomerRequest;
import managment.customerservice.controller.response.ProductClientProductResponse;
import managment.customerservice.model.dto.CustomerDTO;

import java.util.List;


public interface CustomerService {
    Long create(CreateCustomerRequest createCustomerRequest);
    CustomerDTO get(Long customerId);

    List<CustomerDTO> getAll();

    void update(UpdateCustomerRequest updateCustomerRequest, Long customerId);

    void delete(Long customerId);
    void createMany(List<CreateCustomerRequest> createCustomerRequestList);

    ProductClientProductResponse getProduct(Long productId);

    Long getByUsername(String username);

    void updateExternal(UpdateCustomerRequest updateCustomerRequest);
}
