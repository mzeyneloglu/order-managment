package managment.accountservice.controller.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class WalletResponse {
    private String walletName;
    private String walletType;
    private double balance;
    private String date;
    private String expiryDate;

}
