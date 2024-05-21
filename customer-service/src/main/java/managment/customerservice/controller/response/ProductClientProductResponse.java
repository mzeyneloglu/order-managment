package managment.customerservice.controller.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ProductClientProductResponse {
    private String productName;
    private String productDescription;
    private String productCategory;
    private double productPrice;
    private double productDiscount;
    private String message;
    private String productTicketNo;
}
