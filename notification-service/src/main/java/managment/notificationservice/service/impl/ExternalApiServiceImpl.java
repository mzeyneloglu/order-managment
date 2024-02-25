package managment.notificationservice.service.impl;

import lombok.AllArgsConstructor;
import managment.notificationservice.model.Notification;
import managment.notificationservice.repository.NotificationRepository;
import managment.notificationservice.service.ExternalApiService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
public class ExternalApiServiceImpl implements ExternalApiService {
    private NotificationRepository notificationRepository;

    @Override
    public void createNotification(Long customerId, Long orderStatusId, String message) {
        Notification notification = new Notification();
        notification.setCreatedAt(String.valueOf(LocalDateTime.now()));
        notification.setExpiryDate(String.valueOf(LocalDateTime.now().plusYears(1)));
        notification.setMessage(message);
        notification.setCustomerId(customerId);
        notification.setUpdatedAt(String.valueOf(LocalDateTime.now()));
        notification.setOrderStatusId(orderStatusId);

        notificationRepository.save(notification);

    }
}
