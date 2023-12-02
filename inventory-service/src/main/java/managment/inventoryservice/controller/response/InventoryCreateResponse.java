package managment.inventoryservice.controller.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class InventoryCreateResponse {
    private String productName;
    private double productPrice;
    private String productDescription;
    private String productCategory;
    private int quantity;
    private String message;
}
