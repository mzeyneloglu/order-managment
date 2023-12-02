package managment.inventoryservice.controller;

import jakarta.ws.rs.GET;
import lombok.RequiredArgsConstructor;
import managment.inventoryservice.constants.ApiEndpoints;
import managment.inventoryservice.controller.response.InventoryCreateResponse;
import managment.inventoryservice.controller.response.InventoryResponse;
import managment.inventoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.END_POINT)
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("/set-quantity")
    public InventoryCreateResponse create(@RequestParam Long productId,
                                          @RequestParam int quantity) {
        return inventoryService.create(productId, quantity);
    }
    @GetMapping("/get-inventory{inventoryId}")
    public InventoryResponse getInventory(@PathVariable Long inventoryId){
        return inventoryService.getInventory(inventoryId);
    }
    @GetMapping("/get-inventories")
    public List<InventoryResponse> getInventories(){
        return inventoryService.getInventories();
    }

}
