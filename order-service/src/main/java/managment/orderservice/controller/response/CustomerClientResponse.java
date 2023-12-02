package managment.orderservice.controller.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import managment.orderservice.config.OrderConfig.*;

@Getter
@Setter
@Data
@ClientResponse("CustomerDTO from Customer Service")
public class CustomerClientResponse {
    private Long customerId;
    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private String customerAddress;
    private String customerPhone;
}
