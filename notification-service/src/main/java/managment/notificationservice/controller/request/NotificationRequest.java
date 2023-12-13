package managment.notificationservice.controller.request;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class NotificationRequest <T> {
    private String notificationType; // "SMS" or "EMAIL"
    private T messageBody;
    private String date;
    private String status;

}
