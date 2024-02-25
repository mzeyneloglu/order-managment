package managment.orderservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MN_ORDER_DETAILS")
@Getter
@Setter
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "DATE")
    private String date;
}
