package managment.accountservice.controller.response.external;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AccountClientResponse {
    private Long accountId;
    private String accountName;
    private String accountType;
    private String date;
}
