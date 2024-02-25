package managment.notificationservice.controller;

import lombok.RequiredArgsConstructor;
import managment.notificationservice.constants.ApiEndpoints;
import managment.notificationservice.service.ExternalApiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiEndpoints.EXTERNAL_POINT)
@RequiredArgsConstructor
public class ExternalApiController {
    private final ExternalApiService externalApiService;

    @PostMapping("/create-notification/{customerId}/{orderId}/{message}")
    public void createNotification(@PathVariable("customerId") Long customerId,
                                   @PathVariable("orderId") Long orderId,
                                   @PathVariable("message") String message) {
        externalApiService.createNotification(customerId, orderId, message);
    }
}
