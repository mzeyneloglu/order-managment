package managment.orderservice.controller.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class SpeechToTextRequest {
    private List<String> ticketNos;
    private Long customerId;
    private int quantity;
}
