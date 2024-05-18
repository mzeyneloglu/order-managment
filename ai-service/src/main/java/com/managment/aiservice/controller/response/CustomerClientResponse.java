package com.managment.aiservice.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CustomerClientResponse {
    @JsonProperty("customerId")
    private Long customerId;
    @JsonProperty("customerName")
    private String customerName;
    @JsonProperty("customerSurname")
    private String customerSurname;
    @JsonProperty("customerEmail")
    private String customerEmail;
    @JsonProperty("customerAddress")
    private String customerAddress;
    @JsonProperty("customerPhone")
    private String customerPhone;
}
