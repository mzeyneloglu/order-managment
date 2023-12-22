package managment.accountservice.controller.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import managment.accountservice.model.dto.CustomerDTO;

@Data
@Getter
@Setter
public class AccountDeleteResponse {
    private String accountName;
    private String accountType;
    private CustomerDTO customer;
}
