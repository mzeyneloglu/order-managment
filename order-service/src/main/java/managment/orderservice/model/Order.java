package managment.orderservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Setter
@Getter
@Table(name = "MN_ORDER")
public class Order {
    @Id
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1000"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "124")
            }
    )
    @GeneratedValue(generator = "sequence-generator")
    @Column(name = "ID")
    private Long id;

    @Column(name = "ORDER_DETAILS")
    private String orderDetails;

    @Column(name = "DATE_OF_ORDER")
    private String dateOfOrder;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;
}
