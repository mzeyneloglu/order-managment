package com.managment.aiservice.controller;

import com.managment.aiservice.constants.ApiEndpoints;
import com.managment.aiservice.controller.request.SpeechTextRequest;
import com.managment.aiservice.controller.response.OrderResponse;
import com.managment.aiservice.service.SpeechRecognitionService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiEndpoints.END_POINT)
public class SpeechRecognitionController {
    private final SpeechRecognitionService speechRecognitionService;

    @PostMapping("/with-voice-order")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse withVoiceOrder(@RequestBody SpeechTextRequest request) {
        return speechRecognitionService.withVoiceOrder(request);

    }

}
