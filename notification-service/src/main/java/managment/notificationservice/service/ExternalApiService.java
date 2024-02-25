package managment.notificationservice.service;

import org.springframework.stereotype.Service;

@Service
public interface ExternalApiService {
    void createNotification(Long customerId, Long orderStatusId, String message);
}
