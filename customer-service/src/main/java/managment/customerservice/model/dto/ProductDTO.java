package managment.customerservice.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ProductDTO {
    private String productName;
    private double productPrice;
    private String productDescription;
    private String productCategory;
    private String productTicketNo;
}