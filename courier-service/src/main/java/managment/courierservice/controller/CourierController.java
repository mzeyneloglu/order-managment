package managment.courierservice.controller;

import lombok.RequiredArgsConstructor;
import managment.courierservice.constants.ApiEndpoints;
import managment.courierservice.controller.request.CourierRequest;
import managment.courierservice.controller.response.CourierResponse;
import managment.courierservice.service.CourierService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.END_POINT)
@RequiredArgsConstructor
public class CourierController {
    private final CourierService courierService;
    @PostMapping("/create")
    public CourierResponse create(@RequestBody CourierRequest request) {
        return courierService.create(request);
    }

}
