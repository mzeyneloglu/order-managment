package managment.courierservice.service;

import managment.courierservice.controller.request.CourierRequest;
import managment.courierservice.controller.response.CourierResponse;

public interface CourierService {
    CourierResponse create(CourierRequest request);

    String setStatus(Long orderId, Long statusCode);
}
