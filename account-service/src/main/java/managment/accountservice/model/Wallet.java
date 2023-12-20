package managment.accountservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MN_WALLET")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WALLET_ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "BALANCE")
    private double balance;
    @Column(name = "ACCOUNT_ID")
    private Long accountId;
    @Column(name = "WALLET_TYPE")
    private String type;
    @Column(name = "DATE")
    private String creationDate;
    @Column(name = "EXPIRY_DATE")
    private String expiryDate;

}
