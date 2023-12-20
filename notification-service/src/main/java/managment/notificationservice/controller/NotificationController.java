package managment.notificationservice.controller;

import lombok.RequiredArgsConstructor;
import managment.notificationservice.constants.ApiEndpoints;
import managment.notificationservice.controller.request.NotificationRequest;
import managment.notificationservice.service.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.END_POINT)
@RequiredArgsConstructor
public class NotificationController {

}
