package managment.orderservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "MN_ORDER_STATUS")
@Entity
public class OrderStatus {
    @Id
    @Column(name = "ORDER_STATUS_ID")
    private Long id;

    @Column(name = "STATUS")
    private String status;
}
