package managment.orderservice.service.impl;

import lombok.RequiredArgsConstructor;
import managment.orderservice.controller.response.CustomerClientResponse;
import managment.orderservice.controller.response.InventoryClientResponse;
import managment.orderservice.controller.response.OrderResponse;
import managment.orderservice.controller.response.ProductClientResponse;
import managment.orderservice.exception.BusinessLogicException;
import managment.orderservice.model.Order;
import managment.orderservice.model.OrderDetails;
import managment.orderservice.repository.OrderDetailsRepository;
import managment.orderservice.repository.OrderRepository;
import managment.orderservice.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;
    private final OrderDetailsRepository orderDetailsRepository;

    @Override
    public OrderResponse createOrder(Long customerId, Long productId, int quantity) {
        if (ObjectUtils.isEmpty(customerId) || ObjectUtils.isEmpty(productId) || ObjectUtils.isEmpty(quantity)) {
            throw new BusinessLogicException("REQUEST_CANNOT_BE_EMPTY");
        }
        CustomerClientResponse customerClientResponse = restTemplate.getForObject("http://localhost:8182/api/customer/get"+customerId, CustomerClientResponse.class);
        if (ObjectUtils.isEmpty(customerClientResponse)) {
            throw new BusinessLogicException("CUSTOMER_NOT_FOUND");
        }
        ProductClientResponse productClientResponse = restTemplate.getForObject("http://localhost:8181/api/product/get-product/"+productId, ProductClientResponse.class);

        if (ObjectUtils.isEmpty(productClientResponse)) {
            throw new BusinessLogicException("PRODUCT_NOT_FOUND");
        }

        InventoryClientResponse inventoryClientResponse = restTemplate.getForObject("http://localhost:8184/api/inventory/get-inventory-by-product/"+productId, InventoryClientResponse.class);

        if (ObjectUtils.isEmpty(inventoryClientResponse)) {
            throw new BusinessLogicException("INVENTORY_NOT_FOUND");
        }

        if (inventoryClientResponse.getQuantity() < quantity) {
            throw new BusinessLogicException("NOT_FOUND_PRODUCT_QUANTITY_IN_INVENTORY");
        }

        Order order = new Order();
        order.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        order.setCustomerId(customerId);
        order.setOrderDetails(productClientResponse.getProductDescription());
        order.setDateOfOrder(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        orderRepository.save(order);

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setProductId(productId);
        orderDetails.setOrderId(order.getId());
        orderDetails.setDate(order.getDateOfOrder());
        orderDetails.setQuantity(quantity);
        orderDetails.setStatus("1");
        orderDetailsRepository.save(orderDetails);

        restTemplate.postForObject("http://localhost:8184/api/inventory/set-quantity/"+productId+"/"+(inventoryClientResponse.getQuantity()-quantity), null, Void.class);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setQuantity(quantity);
        orderResponse.setCustomerClientResponse(customerClientResponse);
        orderResponse.setProductClientResponse(productClientResponse);
        orderResponse.setDateOfOrder(order.getDateOfOrder());
        orderResponse.setMessage("Order created successfully" + "and updated quantity in stock");

        return orderResponse;
    }
}
