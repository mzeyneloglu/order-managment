package managment.customerservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import managment.customerservice.model.Customer;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    @JsonProperty("customerId")
    private Long customerId;

    @JsonProperty("customerName")
    private String customerName;

    @JsonProperty("customerSurname")
    private String customerSurname;

    @JsonProperty("customerEmail")
    private String customerEmail;

    @JsonProperty("customerPhone")
    private String customerPhone;

    @JsonProperty("customerAddress")
    private String customerAddress;

    @JsonProperty("customerUsername")
    private String customerUsername;

    public CustomerDTO(Customer customer){
        this.customerId = customer.getId();
        this.customerName = customer.getName();
        this.customerSurname = customer.getSurname();
        this.customerEmail = customer.getEmail();
        this.customerPhone = customer.getPhone();
        this.customerAddress = customer.getAddress();
        this.customerUsername = customer.getUsername();
    }
}
