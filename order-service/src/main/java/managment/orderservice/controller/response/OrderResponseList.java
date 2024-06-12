package managment.orderservice.controller.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Data
@Getter
@Setter
public class OrderResponseList {
    List<OrderResponse> orders;
}
