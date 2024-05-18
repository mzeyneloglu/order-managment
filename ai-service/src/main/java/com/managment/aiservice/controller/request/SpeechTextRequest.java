package com.managment.aiservice.controller.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class SpeechTextRequest {
    @JsonProperty("speechText")
    private String speechText;
    @JsonProperty("customerId")
    private Long customerId;
    @JsonProperty("quantity")
    private int quantity;
}
