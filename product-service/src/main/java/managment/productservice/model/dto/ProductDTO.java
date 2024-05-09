package managment.productservice.model.dto;


import lombok.Getter;
import lombok.Setter;
import managment.productservice.model.Product;

@Getter
@Setter
// @ApiModel(value = "Product DTO", description = "Product DTO")
public class ProductDTO {
    private Long productId;
    private String productName;
    private double productPrice;
    private String productDescription;
    private String productCategory;
    private String ticketNo;
    public ProductDTO toDto(Product product) {
        this.productId = product.getId();
        this.productName = product.getName();
        this.productPrice = product.getPrice();
        this.productDescription = product.getDescription();
        this.productCategory = product.getCategory();
        this.ticketNo = product.getTicketNo();
        return this;
    }

}
