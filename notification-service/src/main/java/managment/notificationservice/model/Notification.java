package managment.notificationservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MN_NOTIFICATION")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "MESSAGE")
    private String message;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "CREATED_AT")
    private String createdAt;
    @Column(name = "UPDATED_AT")
    private String updatedAt;
    @Column(name = "EXPIRY_DATE")
    private String expiryDate;
    @Column(name = "CUSTOMER_ID")
    private Long customerId;
}
