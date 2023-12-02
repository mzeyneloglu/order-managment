package managment.orderservice.repository;

import managment.orderservice.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
}