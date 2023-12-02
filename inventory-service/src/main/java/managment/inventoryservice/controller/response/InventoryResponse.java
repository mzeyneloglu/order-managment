package managment.inventoryservice.controller.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class InventoryResponse {
    private Long inventoryId;
    private Long productId;
    private int quantity;

}
