package managment.productservice.controller.request;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Setter
@Getter
// @ApiModel(value = "Product Create Request", description = "Product create request")
public class ProductCreateRequest {
    private String productName;
    private String productDescription;
    private String productCategory;
    private double productPrice;
    private BigDecimal productDiscount;

}
