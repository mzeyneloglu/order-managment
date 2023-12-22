package managment.accountservice.controller.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import managment.accountservice.model.dto.CustomerDTO;

@Getter
@Setter
@Data
public class AccountResponse {
    private String accountName;
    private String accountType;
    private CustomerDTO customerDTO;
    private String date;
}
