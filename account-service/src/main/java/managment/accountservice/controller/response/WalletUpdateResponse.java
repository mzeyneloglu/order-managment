package managment.accountservice.controller.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class WalletUpdateResponse {
    private String walletName;
    private String walletType;
    private double balance;
    private String date;
    private String expiryDate;
}
