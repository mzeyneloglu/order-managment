package managment.courierservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MN_ORDER_INFORMATION")
public class OrderInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;
    @Column(name = "ORDER_ID")
    private long orderId;
    @Column(name = "COURIER_ID")
    private long courierId;
    @Column(name = "STATUS_CODE")
    private String statusCode;

}
