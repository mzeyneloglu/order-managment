package managment.inventoryservice.repository;

import managment.inventoryservice.controller.response.InventoryResponse;
import managment.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findInventoryByProductId(Long productId);
}