package managment.productservice.repository;

import managment.productservice.model.Product;
import managment.productservice.model.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsProductByName (String name);

    @Query("SELECT p.id FROM Product p WHERE p.ticketNo = ?1")
    Long getProductIdByTicketNo(String ticketNo);

}