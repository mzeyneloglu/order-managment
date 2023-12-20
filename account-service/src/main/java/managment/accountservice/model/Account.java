package managment.accountservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MN_ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long id;
    @Column(name = "CUSTOMER_ID")
    private Long customerId;
    @Column(name = "ACCOUNT_NAME")
    private String accountName;
    @Column(name = "ACCOUNT_TYPE")
    private String accountType;
    @Column(name = "DATE")
    private String date;
}
