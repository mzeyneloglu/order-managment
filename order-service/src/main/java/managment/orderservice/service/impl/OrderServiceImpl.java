package managment.orderservice.service.impl;

import managment.orderservice.controller.response.OrderResponse;
import managment.orderservice.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public OrderResponse createOrder(Long customerId, Long productId, int quantity) {

    }
}
