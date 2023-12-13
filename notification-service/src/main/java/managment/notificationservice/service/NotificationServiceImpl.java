package managment.notificationservice.service;

import managment.notificationservice.controller.request.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class NotificationServiceImpl implements NotificationService{
    @Override
    public void sendNotification(NotificationRequest notificationRequest) {
        if (ObjectUtils.isEmpty(notificationRequest)) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (ObjectUtils.isEmpty(notificationRequest.getNotificationType())) {
            throw new IllegalArgumentException("Notification type cannot be null");
        }
        if (ObjectUtils.isEmpty(notificationRequest.getMessageBody())) {
            throw new IllegalArgumentException("MessageBody cannot be null");
        }
        if (ObjectUtils.isEmpty(notificationRequest.getStatus())) {
            throw new IllegalArgumentException("MessageStatus cannot be null");
        }
        if (ObjectUtils.isEmpty(notificationRequest.getDate())) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (notificationRequest.getNotificationType().equals("SMS")) {
            System.out.println("Sending SMS");
        } else if (notificationRequest.getNotificationType().equals("EMAIL")) {
            System.out.println("Sending Email");
        }

    }
}
