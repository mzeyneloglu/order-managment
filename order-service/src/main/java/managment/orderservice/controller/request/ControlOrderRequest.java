package managment.orderservice.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class ControlOrderRequest {
    @JsonProperty("customerId")
    private Long customerId;

    @JsonProperty("productIds")
    private List<Long> productIds;

    @JsonProperty("quantity")
    private int quantity;
}
