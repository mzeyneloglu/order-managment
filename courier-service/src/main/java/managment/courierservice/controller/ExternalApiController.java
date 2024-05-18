package managment.courierservice.controller;

import managment.courierservice.constants.ApiEndpoints;
import managment.courierservice.controller.response.CourierClientResponse;
import managment.courierservice.exception.BusinessLogicException;
import managment.courierservice.service.ExternalApiService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiEndpoints.EXTERNAL_API)
public class ExternalApiController {
    private final ExternalApiService externalApiService;

    public ExternalApiController(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    @PostMapping("/set-courier/{orderId}")
    public CourierClientResponse setCourier(@PathVariable(value = "orderId") Long orderId) {
        return externalApiService.setCourier(orderId);
    }
}
