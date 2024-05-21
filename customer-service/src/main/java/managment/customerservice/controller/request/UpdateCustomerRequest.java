package managment.customerservice.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import managment.customerservice.model.dto.CustomerDTO;

@Getter
@Setter
@Data
public class UpdateCustomerRequest {
    @JsonProperty("customerDto")
    private CustomerDTO customerDto;
}
