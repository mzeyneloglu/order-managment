package managment.notificationservice.service;

import managment.notificationservice.controller.request.NotificationRequest;

public interface NotificationService {
    <T> void sendNotification(NotificationRequest<T> notificationRequest);
}
