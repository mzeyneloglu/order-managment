package com.managment.aiservice.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CourierClientResponse {
    @JsonProperty("packageStatus")
    private String packageStatus;
}
