package managment.productservice.model.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import managment.productservice.model.Product;

import java.math.BigDecimal;
@Getter
@Setter
@Data
public class UpdateProductDTO {
    private String productName;
    private String productDescription;
    private String productCategory;
    private double productPrice;
    private double productDiscount;
    private String productTicketNo;
    private String message;
    private String imageUrl;

    public void toDto(Product product) {
        this.productName = product.getName();
        this.productDescription = product.getDescription();
        this.productCategory = product.getCategory();
        this.productPrice = product.getPrice();
        this.productDiscount = product.getDiscount();
        this.productTicketNo = product.getTicketNo();
        this.imageUrl = product.getImageUrl();
    }

}
