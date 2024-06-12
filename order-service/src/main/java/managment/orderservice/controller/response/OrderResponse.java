package managment.orderservice.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderResponse {
    @JsonProperty("orderId")
    private Long orderId;
    @JsonProperty("customerClientResponse")
    private CustomerClientResponse customerClientResponse;
    @JsonProperty("productClientResponse")
    private ProductClientResponse productClientResponse;
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("dateOfOrder")
    private String dateOfOrder;
    @JsonProperty("courierClientResponse")
    private CourierClientResponse courierClientResponse;
    @JsonProperty("message")
    private String message;

}
