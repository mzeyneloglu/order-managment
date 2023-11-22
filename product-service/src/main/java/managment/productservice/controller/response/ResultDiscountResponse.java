package managment.productservice.controller.response;


import lombok.Getter;
import lombok.Setter;

// @ApiModel(value = "Result Discount Response", description = "Response for applying discount")
@Getter
@Setter
public class ResultDiscountResponse {
    private double newPrice;
    private String message;
}