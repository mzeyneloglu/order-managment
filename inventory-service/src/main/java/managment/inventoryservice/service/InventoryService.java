package managment.inventoryservice.service;

import managment.inventoryservice.controller.response.InventoryCreateResponse;
import managment.inventoryservice.controller.response.InventoryResponse;

import java.util.List;

public interface InventoryService {
    InventoryCreateResponse create(Long productId,  int quantity);

    InventoryResponse getInventory(Long inventoryId);

    List<InventoryResponse> getInventories();
}
