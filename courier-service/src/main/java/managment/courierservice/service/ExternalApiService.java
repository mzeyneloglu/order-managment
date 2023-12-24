package managment.courierservice.service;

import managment.courierservice.controller.response.CourierClientResponse;

public interface ExternalApiService {
    CourierClientResponse setCourier(Long orderId);
}
