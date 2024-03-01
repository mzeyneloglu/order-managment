package managment.accountservice.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AccountDTO {
    private String accountName;
    private String accountType;
    private String date;
}
