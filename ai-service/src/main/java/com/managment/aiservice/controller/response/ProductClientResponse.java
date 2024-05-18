package com.managment.aiservice.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductClientResponse {
    @JsonProperty("productId")
    private Long productId;
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("productPrice")
    private double productPrice;
    @JsonProperty("productDescription")
    private String productDescription;
    @JsonProperty("productCategory")
    private String productCategory;
    @JsonProperty("productTicketNo")
    private String productTicketNo;
}
