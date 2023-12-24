package managment.orderservice.controller.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import managment.orderservice.config.OrderConfig.*;

@Getter
@Setter
@Data
@ClientResponse("Account Response from Product Service")
public class AccountClientResponse {
    private Long accountId;
    private String accountName;
    private String accountType;
    private String date;
}
