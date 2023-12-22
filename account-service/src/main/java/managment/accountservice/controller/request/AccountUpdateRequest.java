package managment.accountservice.controller.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AccountUpdateRequest {
    private String accountName;
    private String accountType;
}
