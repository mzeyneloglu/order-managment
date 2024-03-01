package managment.accountservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import managment.accountservice.model.dto.WalletDTO;

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

    public WalletDTO toDTO() {
        WalletDTO walletDTO = new WalletDTO();
        walletDTO.setName(this.name);
        walletDTO.setBalance(this.balance);
        walletDTO.setAccountId(this.accountId);
        walletDTO.setType(this.type);
        walletDTO.setCreationDate(this.creationDate);
        walletDTO.setExpiryDate(this.expiryDate);
        return walletDTO;
    }

    public Wallet fromDTO(WalletDTO walletDTO) {
        Wallet wallet = new Wallet();
        wallet.setName(walletDTO.getName());
        wallet.setBalance(walletDTO.getBalance());
        wallet.setAccountId(walletDTO.getAccountId());
        wallet.setType(walletDTO.getType());
        wallet.setCreationDate(walletDTO.getCreationDate());
        wallet.setExpiryDate(walletDTO.getExpiryDate());
        return wallet;
    }
}
