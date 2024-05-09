package com.managment.aiservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.managment.aiservice.controller.response.VoiceInfoResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SpeechRecognitionController {
    private final RestTemplate restTemplate;
    private final HttpHeaders headers = new HttpHeaders();

    @GetMapping("/text")
    @ResponseStatus(HttpStatus.OK)
    public VoiceInfoResponse getText() throws JsonProcessingException {
        String url = "http://127.0.0.1:8000/speech-to-text";
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        String jsonResponse = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();

        JSONObject obj = new JSONObject(jsonResponse);
        String ticketNo = obj.getJSONObject("voiceInfoResponse").getString("ticketNo");
        long customerNo = obj.getJSONObject("voiceInfoResponse").getLong("customerNo");
        int quantity = obj.getJSONObject("voiceInfoResponse").getInt("quantity");

        List<String> ticketNoList = Arrays
                .stream(ticketNo.split(" "))
                .toList();

        String pattern = "\\b[aA]\\S*\\b";
        List<String> matches = new ArrayList<>();
        Pattern regex = Pattern.compile(pattern);
        for (String item : ticketNoList) {
            Matcher matcher = regex.matcher(item);
            while (matcher.find()) {
                matches.add(matcher.group());
            }
        }

        VoiceInfoResponse voiceInfoResponse = new VoiceInfoResponse();
        voiceInfoResponse.setTicketNos(matches);
        voiceInfoResponse.setQuantity(quantity);
        voiceInfoResponse.setCustomerNo(customerNo);
        return voiceInfoResponse;
    }

}
