package managment.orderservice.controller;

import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import managment.orderservice.constants.ApiEndpoints;
import managment.orderservice.controller.request.ControlOrderRequest;
import managment.orderservice.controller.request.SpeechToTextRequest;
import managment.orderservice.controller.response.OrderResponse;
import managment.orderservice.controller.response.OrderResponseList;
import managment.orderservice.service.OrderService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.END_POINT)
@RequiredArgsConstructor
@CrossOrigin
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create-order")
    public OrderResponseList createOrder(ControlOrderRequest controlOrderRequest){
        return orderService.createOrder(controlOrderRequest);
    }
    @GetMapping("/get-order/{orderId}")
    public OrderResponse getOrder(@PathVariable Long orderId){
        return orderService.getOrder(orderId);
    }

    @PostMapping("/update-orderStatus/{orderId}/{orderStatusId}")
    public void updateOrderStatus(@PathVariable Long orderId,
                                  @PathVariable Long orderStatusId){
        orderService.updateOrderStatus(orderId, orderStatusId);
    }

    @PostMapping("/create-order-with-voice")
    public OrderResponseList createOrderWithVoice(@RequestBody SpeechToTextRequest request){
        return orderService.createOrderWithVoice(request);
    }

    @GetMapping("/get-orders/{customerId}")
    public OrderResponseList getOrders(@PathVariable Long customerId){
        return orderService.getOrders(customerId);
    }

    @PostMapping("/delete-order/{orderId}")
    public void deleteOrder(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
    }

}
