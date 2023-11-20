package managment.orderservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "MN_ORDER")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ORDER_DETAILS")
    private String orderDetails;

    @Column(name = "DATE_OF_ORDER")
    private String dateOfOrder;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;
}
