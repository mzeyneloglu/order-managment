package managment.courierservice.controller.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CourierRequest {
    private String courierName;
    private String courierSurname;
    private String courierCompany;
    private String courierPhone;
    private String courierStatus;
    private int crPackageNumber;
}
