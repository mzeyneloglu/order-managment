package managment.productservice.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ExternalProductRequest {
    @JsonProperty("ticketNo")
    private String ticketNo;
}
