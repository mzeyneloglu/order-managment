package managment.orderservice.controller.response;

import lombok.Getter;
import lombok.Setter;
import managment.orderservice.config.OrderConfig.*;

@Getter
@Setter
@ClientResponse
public class InventoryClientResponse {
    private Long inventoryId;
    private Long productId;
    private int quantity;
}
