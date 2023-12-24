package managment.courierservice.repository;

import managment.courierservice.model.OrderInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderInformationRepository extends JpaRepository<OrderInformation, Long> {
}