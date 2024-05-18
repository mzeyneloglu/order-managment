package com.managment.aiservice.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class OrderResponse {
    @JsonProperty("customerClientResponse")
    private CustomerClientResponse customerClientResponse;
    @JsonProperty("productClientResponse")
    private ProductClientResponse productClientResponse;
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("dateOfOrder")
    private String dateOfOrder;
    @JsonProperty("courierClientResponse")
    private CourierClientResponse courierClientResponse;
    @JsonProperty("message")
    private String message;
}
