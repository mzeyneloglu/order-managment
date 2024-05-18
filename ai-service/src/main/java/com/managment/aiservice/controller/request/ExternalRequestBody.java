package com.managment.aiservice.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class ExternalRequestBody {
    @JsonProperty("ticketNos")
    private List<String> ticketNos;
    @JsonProperty("customerId")
    private Long customerId;
    @JsonProperty("quantity")
    private int quantity;
}
