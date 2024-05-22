package managment.accountservice.controller.response.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GeneralAccountResponse {
    @JsonProperty("accountName")
    private String accountName;

    @JsonProperty("accountType")
    private String accountType;

    @JsonProperty("walletName")
    private String walletName;

    @JsonProperty("balance")
    private double balance;

    @JsonProperty("createdAt")
    private String createdAt;

    @JsonProperty("expiryDate")
    private String expiryDate;

}
