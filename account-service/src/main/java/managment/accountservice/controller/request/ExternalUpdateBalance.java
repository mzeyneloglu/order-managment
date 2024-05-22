package managment.accountservice.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ExternalUpdateBalance {
    @JsonProperty("balance")
    private double balance;

    @JsonProperty("customerId")
    private Long customerId;
}
