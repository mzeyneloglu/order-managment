package com.managment.aiservice.service;

import com.managment.aiservice.controller.request.ExternalRequestBody;
import com.managment.aiservice.controller.request.SpeechTextRequest;
import com.managment.aiservice.controller.response.CourierClientResponse;
import com.managment.aiservice.controller.response.CustomerClientResponse;
import com.managment.aiservice.controller.response.OrderResponse;
import com.managment.aiservice.controller.response.ProductClientResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
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

    public List<OrderResponse> withVoiceOrder(SpeechTextRequest request) {
        JSONObject obj = new JSONObject(request);
        String text = obj.getString("speechText");
        Long customerId = obj.getLong("customerId");
        int quantity = obj.getInt("quantity");

        List<String> matches = getTicketNos(text);
        // ------ logged ------
        log.info("matches: {}", matches);

        String url = "http://localhost:8183/api/order/create-order-with-voice";

        ExternalRequestBody externalRequestBody = new ExternalRequestBody();
        externalRequestBody.setCustomerId(customerId);
        externalRequestBody.setQuantity(quantity);
        externalRequestBody.setTicketNos(matches);
        log.info("externalRequestBody: {}", externalRequestBody);

        headers.set("Content-Type", "application/json");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ExternalRequestBody> requestEntity = new HttpEntity<>(externalRequestBody, headers);

        String jsonResponse = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray ordersArray = jsonObject.getJSONArray("orders");

        List<OrderResponse> orderResponses = new ArrayList<>();

        for (int i = 0; i < ordersArray.length(); i++) {
            JSONObject orderObject = ordersArray.getJSONObject(i);

            CustomerClientResponse customerClientResponse = new CustomerClientResponse();
            ProductClientResponse productClientResponse = new ProductClientResponse();
            CourierClientResponse courierClientResponse = new CourierClientResponse();
            OrderResponse orderResponse = new OrderResponse();

            JSONObject customerObject = orderObject.getJSONObject("customerClientResponse");
            customerClientResponse.setCustomerAddress(customerObject.getString("customerAddress"));
            customerClientResponse.setCustomerEmail(customerObject.getString("customerEmail"));
            customerClientResponse.setCustomerName(customerObject.getString("customerName"));
            customerClientResponse.setCustomerPhone(customerObject.getString("customerPhone"));
            customerClientResponse.setCustomerSurname(customerObject.getString("customerSurname"));
            customerClientResponse.setCustomerId(customerObject.getLong("customerId"));

            JSONObject productObject = orderObject.getJSONObject("productClientResponse");
            productClientResponse.setProductCategory(productObject.getString("productCategory"));
            productClientResponse.setProductDescription(productObject.getString("productDescription"));
            productClientResponse.setProductPrice(productObject.getDouble("productPrice"));
            productClientResponse.setProductTicketNo(productObject.getString("productTicketNo"));
            productClientResponse.setProductName(productObject.getString("productName"));
            productClientResponse.setProductId(productObject.getLong("productId"));
            productClientResponse.setImageUrl(productObject.getString("imageUrl"));

            JSONObject courierObject = orderObject.getJSONObject("courierClientResponse");
            courierClientResponse.setPackageStatus(courierObject.getString("packageStatus"));

            orderResponse.setDateOfOrder(orderObject.getString("dateOfOrder"));
            orderResponse.setQuantity(orderObject.getInt("quantity"));
            orderResponse.setMessage(orderObject.getString("message"));
            orderResponse.setCustomerClientResponse(customerClientResponse);
            orderResponse.setProductClientResponse(productClientResponse);
            orderResponse.setCourierClientResponse(courierClientResponse);

            orderResponses.add(orderResponse);
        }

        return orderResponses;

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
