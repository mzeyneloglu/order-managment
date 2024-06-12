package managment.orderservice.model.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class OrderInfoDTO {
    private Long id;
    private String productName;
    private String date;
    private int quantity;
    private double price;
    private Long orderId;
    private Long customerId;
    private Long productId;
    private String status;
}
