package managment.notificationservice.model.dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NotificationDTO <T> {
    private Long id;
    private String messageType;
    private T messageBody;
    private String messageStatus;
    private String date;

}
