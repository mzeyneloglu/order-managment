package managment.inventoryservice.controller.response;

import lombok.Getter;
import lombok.Setter;
import managment.inventoryservice.config.InventoryConfig.*;

@Getter
@Setter
@ClientResponse("Return ProductDTO from Product Service")
public class ProductClientResponse {
    private Long productId;
    private String productName;
    private double productPrice;
    private String productDescription;
    private String productCategory;
}
