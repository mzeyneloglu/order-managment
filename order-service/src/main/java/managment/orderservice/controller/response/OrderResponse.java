package managment.orderservice.controller.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderResponse {
    private CustomerClientResponse customerClientResponse;
    private ProductClientResponse productClientResponse;
    private int quantity;
    private String dateOfOrder;
    private String message;

}
