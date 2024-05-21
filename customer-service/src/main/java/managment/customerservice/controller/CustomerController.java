package managment.customerservice.controller;

import lombok.RequiredArgsConstructor;
import managment.customerservice.constants.ApiEndpoints;
import managment.customerservice.controller.request.CreateCustomerRequest;
import managment.customerservice.controller.request.UpdateCustomerRequest;
import managment.customerservice.controller.response.ProductClientProductResponse;
import managment.customerservice.model.dto.CustomerDTO;
import managment.customerservice.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.END_POINT)
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Long create(@RequestBody CreateCustomerRequest createCustomerRequest){
        return customerService.create(createCustomerRequest);
    }

    @PostMapping("/create-many-customers")
    @ResponseStatus(HttpStatus.OK)
    public void createMany(@RequestBody List<CreateCustomerRequest> createCustomerRequestList){
        customerService.createMany(createCustomerRequestList);
    }

    @GetMapping("/get{customerId}")
    public CustomerDTO get(@PathVariable Long customerId){
        return customerService.get(customerId);
    }

    @GetMapping("/get-all")
    public List<CustomerDTO> getAll(){
        return customerService.getAll();
    }

    @PostMapping("/update")
    public void update(@RequestBody UpdateCustomerRequest updateCustomerRequest,
                       @RequestParam Long customerId){
        customerService.update(updateCustomerRequest, customerId);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam Long customerId){
        customerService.delete(customerId);
    }
    @GetMapping("/get-product")
    public ProductClientProductResponse getProduct(@RequestHeader Long productId){
        return customerService.getProduct(productId);
    }
    @GetMapping("get-id-by-username/{username}")
    public Long getByUsername(@PathVariable String username){
        return customerService.getByUsername(username);
    }

    @PostMapping("/update-external")
    public void updateExternal(@RequestBody UpdateCustomerRequest updateCustomerRequest){
        customerService.updateExternal(updateCustomerRequest);
    }
}
