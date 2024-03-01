package managment.accountservice.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class WalletDTO {
    private String name;
    private Double balance;
    private Long accountId;
    private String type;
    private String creationDate;
    private String expiryDate;
}
