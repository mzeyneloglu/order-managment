package com.managment.aiservice.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@JsonView(VoiceInfoResponse.class)
public class VoiceInfoResponse {
    @JsonProperty("ticketNo")
    private List<String> ticketNos;

    @JsonProperty("customerNo")
    private Long customerNo;

    @JsonProperty("quantity")
    private int quantity;
}
