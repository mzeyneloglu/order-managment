package managment.orderservice.controller;

import lombok.RequiredArgsConstructor;
import managment.orderservice.constants.ApiEndpoints;
import managment.orderservice.controller.response.OrderResponse;
import managment.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.END_POINT)
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create-order")
    public OrderResponse createOrder(@RequestHeader Long customerId,
                                     @RequestHeader Long productId,
                                     @RequestHeader int quantity){
        return orderService.createOrder(customerId, productId, quantity);
    }
}
