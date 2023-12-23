package managment.productservice.controller.request;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ProductCreateRequest {
    private String productName;
    private String productDescription;
    private String productCategory;
    private double productPrice;
    private double productDiscount;
    private String productCode;

}
