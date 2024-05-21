package managment.customerservice.repository;

import managment.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c.id FROM Customer c WHERE c.username = ?1")
    Long getCustomerIdByCustomerUsername(String username);

    Customer findCustomerById(Long id);
}