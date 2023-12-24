package managment.inventoryservice.service.impl;

import lombok.RequiredArgsConstructor;
import managment.inventoryservice.controller.response.InventoryCreateResponse;
import managment.inventoryservice.controller.response.InventoryResponse;
import managment.inventoryservice.controller.response.ProductClientResponse;
import managment.inventoryservice.exception.BusinessLogicException;
import managment.inventoryservice.model.Inventory;
import managment.inventoryservice.repository.InventoryRepository;
import managment.inventoryservice.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final RestTemplate restTemplate;
    @Override
    public InventoryCreateResponse create(Long productId, int quantity) {
        ProductClientResponse productClientResponse = restTemplate.getForObject("http://localhost:8181/api/product/get-product/"
                + productId, ProductClientResponse.class);

        if (ObjectUtils.isEmpty(productClientResponse))
            throw new BusinessLogicException("PRODUCT_NOT_FOUND");

        Inventory inventory = new Inventory();
        inventory.setQuantity(quantity);
        inventory.setProductId(productId);
        inventory.setStatus("1");
        inventoryRepository.save(inventory);

        InventoryCreateResponse inventoryCreateResponse = new InventoryCreateResponse();
        inventoryCreateResponse.setQuantity(quantity);
        inventoryCreateResponse.setProductCategory(productClientResponse.getProductCategory());
        inventoryCreateResponse.setProductName(productClientResponse.getProductName());
        inventoryCreateResponse.setProductPrice(productClientResponse.getProductPrice());
        inventoryCreateResponse.setProductDescription(productClientResponse.getProductDescription());
        inventoryCreateResponse.setMessage("Product quantity set successfully");
        return inventoryCreateResponse;

    }

    @Override
    public InventoryResponse getInventory(Long inventoryId) {
        if (ObjectUtils.isEmpty(inventoryId)){
            throw new BusinessLogicException("INVENTORY_ID_NOT_FOUND");
        }
        Inventory inventory = inventoryRepository.findById(inventoryId).orElseThrow();
        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setInventoryId(inventory.getId());
        inventoryResponse.setQuantity(inventory.getQuantity());
        inventoryResponse.setProductId(inventory.getProductId());

        return inventoryResponse;
    }

    @Override
    public List<InventoryResponse> getInventories() {
        return inventoryRepository.findAll().stream().map(inventory -> {
            InventoryResponse inventoryResponse = new InventoryResponse();
            inventoryResponse.setInventoryId(inventory.getId());
            inventoryResponse.setQuantity(inventory.getQuantity());
            inventoryResponse.setProductId(inventory.getProductId());
            return inventoryResponse;
        }).collect(Collectors.toList());
    }

    @Override
    public InventoryResponse getInventoryByProduct(Long productId) {
        Inventory inventory = inventoryRepository.findInventoryByProductId(productId).orElseThrow();
        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setInventoryId(inventory.getId());
        inventoryResponse.setQuantity(inventory.getQuantity());
        inventoryResponse.setProductId(inventory.getProductId());
        return inventoryResponse;
    }
    @Override
    public void updateQuantity(int quantity, Long productId) {
        Inventory inventory = inventoryRepository.findInventoryByProductId(productId).orElseThrow(() -> new BusinessLogicException("INVENTORY_NOT_FOUND"));
        inventory.setQuantity(quantity);
        inventoryRepository.save(inventory);
    }
}
