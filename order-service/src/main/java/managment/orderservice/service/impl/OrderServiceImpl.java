package managment.orderservice.service.impl;

import lombok.RequiredArgsConstructor;
import managment.orderservice.constants.OrderStatusCode;
import managment.orderservice.controller.response.*;
import managment.orderservice.exception.BusinessLogicConstants;
import managment.orderservice.exception.BusinessLogicException;
import managment.orderservice.model.Order;
import managment.orderservice.model.OrderDetails;
import managment.orderservice.model.OrderStatus;
import managment.orderservice.repository.OrderDetailsRepository;
import managment.orderservice.repository.OrderRepository;
import managment.orderservice.repository.OrderStatusRepository;
import managment.orderservice.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderStatusRepository orderStatusRepository;

    @Override
    public OrderResponse createOrder(Long customerId, Long productId, int quantity) {
        if (ObjectUtils.isEmpty(customerId) || ObjectUtils.isEmpty(productId) || ObjectUtils.isEmpty(quantity))
            throw new BusinessLogicException(BusinessLogicConstants.PR1001);

        if (quantity == 0)
            throw new BusinessLogicException(BusinessLogicConstants.PR1008);

        CustomerClientResponse customerClientResponse = restTemplate.getForObject("http://localhost:9191/api/customer/get"+customerId, CustomerClientResponse.class);
        if (ObjectUtils.isEmpty(customerClientResponse))
            throw new BusinessLogicException(BusinessLogicConstants.PR1004);

        ProductClientResponse productClientResponse = restTemplate.getForObject("http://localhost:9191/api/product/get-product/"+productId, ProductClientResponse.class);

        if (ObjectUtils.isEmpty(productClientResponse))
            throw new BusinessLogicException(BusinessLogicConstants.PR1004);

        InventoryClientResponse inventoryClientResponse = restTemplate.getForObject("http://localhost:9191/api/inventory/get-inventory-by-product/"+productId, InventoryClientResponse.class);

        if (ObjectUtils.isEmpty(inventoryClientResponse))
            throw new BusinessLogicException(BusinessLogicConstants.PR1004);

        if (inventoryClientResponse.getQuantity() < quantity)
            throw new BusinessLogicException(BusinessLogicConstants.PR1006);

        AccountClientResponse accountClientResponse = restTemplate.getForObject("http://localhost:9191/api/account/external/get-account/"+customerId, AccountClientResponse.class);

        if (ObjectUtils.isEmpty(accountClientResponse))
            throw new BusinessLogicException(BusinessLogicConstants.PR1004);

        WalletClientResponse walletClientResponse = restTemplate.getForObject("http://localhost:9191/api/account/external/get-wallet/" + accountClientResponse.getAccountId(), WalletClientResponse.class);

        if (ObjectUtils.isEmpty(walletClientResponse))
            throw new BusinessLogicException(BusinessLogicConstants.PR1004);

        if (walletClientResponse.getBalance() < (productClientResponse.getProductPrice() * quantity))
            throw new BusinessLogicException(BusinessLogicConstants.PR1005);

        Order order = new Order();
        order.setCustomerId(customerId);
        order.setOrderDetails(productClientResponse.getProductDescription());
        order.setDateOfOrder(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        orderRepository.save(order);

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setProductId(productId);
        orderDetails.setOrderId(order.getId());
        orderDetails.setDate(order.getDateOfOrder());
        orderDetails.setQuantity(quantity);
        orderDetailsRepository.save(orderDetails);

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(Long.valueOf(OrderStatusCode.CREATED.getCode()));
        orderStatus.setStatus(String.valueOf(OrderStatusCode.CREATED));
        orderStatus.setOrderId(order.getId());
        orderStatusRepository.save(orderStatus);

        restTemplate.postForObject("http://localhost:9191/api/notification/external/create-notification/" +
                customerClientResponse.getCustomerId() +
                    "/" + orderStatus.getId() +
                    "/" + orderStatus.getStatus(),
                null, Void.class);


        restTemplate.postForObject("http://localhost:9191/api/account/external/update-balance/" + accountClientResponse.getAccountId() + "/" + (productClientResponse.getProductPrice() * quantity), null, Void.class);

        CourierClientResponse courierClientResponse = restTemplate.postForObject("http://localhost:9191/api/courier/set-courier/" + order.getId(),
                null,
                CourierClientResponse.class);

        restTemplate.postForObject("http://localhost:9191/api/inventory/set-quantity/" + productId + "/" + (inventoryClientResponse.getQuantity()-quantity), null, Void.class);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setQuantity(quantity);
        orderResponse.setCustomerClientResponse(customerClientResponse);
        orderResponse.setProductClientResponse(productClientResponse);
        orderResponse.setDateOfOrder(order.getDateOfOrder());
        orderResponse.setCourierClientResponse(courierClientResponse);
        orderResponse.setMessage("Order created successfully");
        return orderResponse;
    }
}
