package managment.customerservice.controller.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import managment.customerservice.model.dto.CustomerDTO;

@Getter
@Setter
@Data
public class UpdateCustomerRequest {
    private CustomerDTO customerDto;
}
