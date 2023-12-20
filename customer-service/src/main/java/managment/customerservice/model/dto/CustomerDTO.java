package managment.customerservice.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import managment.customerservice.model.Customer;

@Getter
@Setter
@Data
public class CustomerDTO {
    private Long customerId;
    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private String customerPhone;
    private String customerAddress;

    public CustomerDTO(Customer customer){
        this.customerId = customer.getId();
        this.customerName = customer.getName();
        this.customerSurname = customer.getSurname();
        this.customerEmail = customer.getEmail();
        this.customerPhone = customer.getPhone();
        this.customerAddress = customer.getAddress();
    }
}
