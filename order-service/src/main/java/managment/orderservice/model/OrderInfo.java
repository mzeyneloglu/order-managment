package managment.orderservice.model;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "mn_order_info",
        indexes = {@Index(name = "IDX_CUSTOMER_ID", columnList="customerId"),
                   @Index(name = "IDX_PRODUCT_ID", columnList="productId"),
                   @Index(name = "IDX_ORDER_ID", columnList="orderId")})
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "DATE")
    private String date;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "ORDER_ID")
    private Long orderId;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "TICKET_NO")
    private String ticketNo;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "IMG_SRC")
    private String imgSrc;

}
