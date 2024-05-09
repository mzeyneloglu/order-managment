package managment.orderservice.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import managment.orderservice.config.OrderConfig.*;

@Data
@Getter
@Setter
@ClientResponse
public class VoiceOrderInfoResponse {
    @JsonProperty(value = "ticketNo")
    private String ticketNo;
    @JsonProperty(value = "customerId")
    private Long customerId;
    @JsonProperty(value = "quantity")
    private int quantity;
}
