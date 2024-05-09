package managment.accountservice.controller.response.external;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class WalletClientResponse {
    private Long walletId;
    private String walletType;
    private String date;
    private String walletName;
    private double balance;
}
