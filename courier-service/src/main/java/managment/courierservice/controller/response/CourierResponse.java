package managment.courierservice.controller.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CourierResponse {
    private String courierName;
    private String courierSurname;
    private String courierCompany;
    private String courierPhone;
}
