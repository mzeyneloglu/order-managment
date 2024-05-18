package managment.orderservice.controller;

import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import managment.orderservice.constants.ApiEndpoints;
import managment.orderservice.controller.request.SpeechToTextRequest;
import managment.orderservice.controller.response.OrderResponse;
import managment.orderservice.service.OrderService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiEndpoints.END_POINT)
@RequiredArgsConstructor
@CrossOrigin
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create-order")
    public OrderResponse createOrder(@RequestHeader Long customerId,
                                     @RequestHeader Long productId,
                                     @RequestHeader int quantity){
        return orderService.createOrder(customerId, productId, quantity);
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
    public OrderResponse createOrderWithVoice(@RequestBody SpeechToTextRequest request){
        return orderService.createOrderWithVoice(request);
    }

}
