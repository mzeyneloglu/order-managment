package managment.orderservice.controller.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import managment.orderservice.config.OrderConfig.*;

@Getter
@Setter
@Data
@ClientResponse("Wallet Response from Product Service")
public class WalletClientResponse {
    private Long walletId;
    private String walletType;
    private String date;
    private String walletName;
    private double balance;
}
