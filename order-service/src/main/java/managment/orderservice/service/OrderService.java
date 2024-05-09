package managment.orderservice.service;

import managment.orderservice.controller.response.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(Long customerId, Long productId, int quantity);

    OrderResponse getOrder(Long orderId);

    void updateOrderStatus(Long orderId,Long orderStatusId);

    OrderResponse createOrderWithVoice();
}
