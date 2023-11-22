package managment.productservice.controller.response;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
// @ApiModel(description = "Product Create Response")
public class ProductCreateResponse {
    private String productName;
    private String productDescription;
    private String productCategory;
    private String productPrice;
    private BigDecimal productDiscount;
    private String message;

}
