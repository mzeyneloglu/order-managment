package managment.orderservice.constants;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum OrderStatusCode {
    GETTING_READY("101", "Order is being prepared."),
    CREATED("200", "The order has been created."),
    SET_OUT("202", "The order is on its way."),
    REACHED_TRANSFER_ZONE("301","The order has arrived at the transfer zone."),
    REACHED_BRANCH("303","Your order has arrived at the dealer."),
    DELIVERY_OUT("305","Your order is out for distribution."),
    DELIVERED("401","Your order has been delivered."),
    NOT_DELIVERED("501", "Your order could not be delivered."),
    CANCELLED("502","Your order has been cancelled.");

    private String description;
    private String code;
    OrderStatusCode(String code, String description) {
        this.description = description;
        this.code = code;

    }
}



