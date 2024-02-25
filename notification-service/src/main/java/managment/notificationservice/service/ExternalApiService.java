package managment.notificationservice.service;


public interface ExternalApiService {
    void createNotification(Long customerId, Long orderStatusId, String message);
}
