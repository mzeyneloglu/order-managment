package managment.orderservice.repository;

import managment.orderservice.model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {
    List<OrderInfo> findAllByCustomerId(Long customerId);

    OrderInfo findByOrderId(Long orderId);
}
