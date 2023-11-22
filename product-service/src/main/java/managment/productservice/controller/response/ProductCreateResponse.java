package managment.productservice.controller.response;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import managment.productservice.model.Product;

import java.math.BigDecimal;

@Data
@Getter
@Setter
// @ApiModel(description = "Product Create Response")
public class ProductCreateResponse {
    private String productName;
    private String productDescription;
    private String productCategory;
    private double productPrice;
    private double productDiscount;
    private String message;

    public void toDto(Product product){
        this.setProductName(product.getName());
        this.setProductDescription(product.getDescription());
        this.setProductCategory(product.getCategory());
        this.setProductPrice(product.getPrice());
        this.setProductDiscount(product.getDiscount());
    }

}
