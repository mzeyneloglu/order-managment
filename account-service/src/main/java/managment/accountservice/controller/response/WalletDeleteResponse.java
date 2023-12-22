package managment.accountservice.controller.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class WalletDeleteResponse {
    private WalletResponse walletResponse;
    private AccountResponse accountResponse;
    private String message;
}
