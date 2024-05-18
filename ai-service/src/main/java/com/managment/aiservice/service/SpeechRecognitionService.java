package com.managment.aiservice.service;

import com.managment.aiservice.controller.request.ExternalRequestBody;
import com.managment.aiservice.controller.request.SpeechTextRequest;
import com.managment.aiservice.controller.response.CourierClientResponse;
import com.managment.aiservice.controller.response.CustomerClientResponse;
import com.managment.aiservice.controller.response.OrderResponse;
import com.managment.aiservice.controller.response.ProductClientResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class SpeechRecognitionService {
    private final RestTemplate restTemplate;
    private final HttpHeaders headers = new HttpHeaders();

    public OrderResponse withVoiceOrder(SpeechTextRequest request) {
        JSONObject obj = new JSONObject(request);
        String text = obj.getString("speechText");
        Long customerId = obj.getLong("customerId");
        int quantity = obj.getInt("quantity");

        List<String> matches = getTicketNos(text);
        // ------ logged ------
        log.info("matches: {}", matches);

        String url = "http://localhost:9191/order-service/api/order/create-order-with-voice";

        ExternalRequestBody externalRequestBody = new ExternalRequestBody();
        externalRequestBody.setCustomerId(customerId);
        externalRequestBody.setQuantity(quantity);
        externalRequestBody.setTicketNos(matches);
        log.info("externalRequestBody: {}", externalRequestBody);

        headers.set("Content-Type", "application/json");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ExternalRequestBody> requestEntity = new HttpEntity<>(externalRequestBody, headers);

        String jsonResponse = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();

        CustomerClientResponse customerClientResponse = new CustomerClientResponse();
        ProductClientResponse productClientResponse = new ProductClientResponse();
        CourierClientResponse courierClientResponse = new CourierClientResponse();
        OrderResponse orderResponse = new OrderResponse();

        JSONObject response = new JSONObject(jsonResponse);
        log.info("response: {}", response);
        customerClientResponse.setCustomerAddress(response.getJSONObject("customerClientResponse").getString("customerAddress"));
        customerClientResponse.setCustomerEmail(response.getJSONObject("customerClientResponse").getString("customerEmail"));
        customerClientResponse.setCustomerName(response.getJSONObject("customerClientResponse").getString("customerName"));
        customerClientResponse.setCustomerPhone(response.getJSONObject("customerClientResponse").getString("customerPhone"));
        customerClientResponse.setCustomerSurname(response.getJSONObject("customerClientResponse").getString("customerSurname"));
        customerClientResponse.setCustomerId(response.getJSONObject("customerClientResponse").getLong("customerId"));

        productClientResponse.setProductCategory(response.getJSONObject("productClientResponse").getString("productCategory"));
        productClientResponse.setProductDescription(response.getJSONObject("productClientResponse").getString("productDescription"));
        productClientResponse.setProductPrice(response.getJSONObject("productClientResponse").getDouble("productPrice"));
        productClientResponse.setProductTicketNo(response.getJSONObject("productClientResponse").getString("productTicketNo"));
        productClientResponse.setProductName(response.getJSONObject("productClientResponse").getString("productName"));
        productClientResponse.setProductId(response.getJSONObject("productClientResponse").getLong("productId"));

        courierClientResponse.setPackageStatus(response.getJSONObject("courierClientResponse").getString("packageStatus"));

        orderResponse.setDateOfOrder(response.getString("dateOfOrder"));
        orderResponse.setQuantity(response.getInt("quantity"));
        orderResponse.setMessage(response.getString("message"));
        orderResponse.setCustomerClientResponse(customerClientResponse);
        orderResponse.setProductClientResponse(productClientResponse);
        orderResponse.setCourierClientResponse(courierClientResponse);
        return orderResponse;

    }

    private static List<String> getTicketNos(String text) {
        List<String> text_split = Arrays
                .stream(text.split(" "))
                .toList();

        String pattern = "\\b[aA]\\S*\\b";
        List<String> matches = new ArrayList<>();
        Pattern regex = Pattern.compile(pattern);
        for (String item : text_split) {
            Matcher matcher = regex.matcher(item);
            while (matcher.find()) {
                matches.add(matcher.group());
            }
        }
        return matches;
    }
}
