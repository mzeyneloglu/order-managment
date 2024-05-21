package managment.authservice.controller.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer ";
    private Long customerId;

    public AuthResponse(String accessToken, Long customerId) {
        this.accessToken = accessToken;
        this.customerId = customerId;
    }

}
