package managment.authservice.controller.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class RegistrationRequest {
    
    private String name;
    private String surname;
    private String username;
    private String email;
    private String address;
    private String phone;
    private String password;
}
