package managment.orderservice.controller.response;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import managment.orderservice.config.OrderConfig.*;

@Getter
@Setter
@Data
@ClientResponse("ProductDTO from Product Service")
public class ProductClientResponse {
    private Long productId;
    private String productName;
    private double productPrice;
    private String productDescription;
    private String productCategory;
    private String productTicketNo;
    private String imageUrl;
}
