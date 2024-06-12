package managment.orderservice.service;

import managment.orderservice.controller.request.ControlOrderRequest;
import managment.orderservice.controller.request.SpeechToTextRequest;
import managment.orderservice.controller.response.OrderResponse;
import managment.orderservice.controller.response.OrderResponseList;

import java.util.List;

public interface OrderService {
    OrderResponseList createOrder(ControlOrderRequest controlOrderRequest);

    OrderResponse getOrder(Long orderId);

    void updateOrderStatus(Long orderId,Long orderStatusId);

    OrderResponseList createOrderWithVoice(SpeechToTextRequest request);

    OrderResponseList getOrders(Long customerId);

    void deleteOrder(Long orderId);
}
