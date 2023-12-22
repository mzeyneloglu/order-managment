package managment.accountservice.controller.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class WalletRequest {
    private String name;
    private double balance;
    private String walletType;
    private String date;
    private String expiryDate;
    private Long accountId;
}
