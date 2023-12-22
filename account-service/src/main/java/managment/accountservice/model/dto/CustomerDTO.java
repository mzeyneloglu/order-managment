package managment.accountservice.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CustomerDTO {
    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private String customerPhone;
    private String customerAddress;
}
