package managment.orderservice.service;

import managment.orderservice.controller.response.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(Long customerId, Long productId, int quantity);
}
