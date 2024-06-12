package managment.orderservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import managment.orderservice.constants.OrderStatusCode;
import managment.orderservice.controller.request.ControlOrderRequest;
import managment.orderservice.controller.request.SpeechToTextRequest;
import managment.orderservice.controller.response.*;
import managment.orderservice.exception.BusinessLogicConstants;
import managment.orderservice.exception.BusinessLogicException;
import managment.orderservice.model.Order;
import managment.orderservice.model.OrderDetails;
import managment.orderservice.model.OrderInfo;
import managment.orderservice.model.OrderStatus;
import managment.orderservice.repository.OrderDetailsRepository;
import managment.orderservice.repository.OrderInfoRepository;
import managment.orderservice.repository.OrderRepository;
import managment.orderservice.repository.OrderStatusRepository;
import managment.orderservice.service.OrderService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final OrderInfoRepository orderInfoRepository;
    private final HttpHeaders headers = new HttpHeaders();

    @Override
    public OrderResponseList createOrder(ControlOrderRequest controlOrderRequest) {
        if(ObjectUtils.isEmpty(controlOrderRequest))
            throw new BusinessLogicException(BusinessLogicConstants.PR1001);

        Long customerId = controlOrderRequest.getCustomerId();
        int quantity = controlOrderRequest.getQuantity();
        List<Long> productIds = controlOrderRequest.getProductIds();

        OrderResponseList orderResponseList = new OrderResponseList();
        List<OrderResponse> orderResponses = new ArrayList<>();
        productIds.forEach(productId ->{
            if (ObjectUtils.isEmpty(customerId) || ObjectUtils.isEmpty(productId) || ObjectUtils.isEmpty(quantity))
                throw new BusinessLogicException(BusinessLogicConstants.PR1001);

            if (quantity == 0)
                throw new BusinessLogicException(BusinessLogicConstants.PR1008);

            CustomerClientResponse customerClientResponse = restTemplate.getForObject("http://localhost:9191/customer-service/api/customer/get"+customerId, CustomerClientResponse.class);
            if (ObjectUtils.isEmpty(customerClientResponse))
                throw new BusinessLogicException(BusinessLogicConstants.PR1004);

            ProductClientResponse productClientResponse = restTemplate.getForObject("http://localhost:9191/product-service/api/product/get-product/"+productId, ProductClientResponse.class);
            if (ObjectUtils.isEmpty(productClientResponse))
                throw new BusinessLogicException(BusinessLogicConstants.PR1004);

            InventoryClientResponse inventoryClientResponse = restTemplate.getForObject("http://localhost:9191/inventory-service/api/inventory/get-inventory-by-product/"+productId, InventoryClientResponse.class);

            if (ObjectUtils.isEmpty(inventoryClientResponse))
                throw new BusinessLogicException(BusinessLogicConstants.PR1004);

            if (inventoryClientResponse.getQuantity() < quantity)
                throw new BusinessLogicException(BusinessLogicConstants.PR1006);

            AccountClientResponse accountClientResponse = restTemplate.getForObject("http://localhost:9191/account-service/api/account/external/get-account/"+customerId, AccountClientResponse.class);

            if (ObjectUtils.isEmpty(accountClientResponse))
                throw new BusinessLogicException(BusinessLogicConstants.PR1004);

            WalletClientResponse walletClientResponse = restTemplate.getForObject("http://localhost:9191/account-service/api/account/external/get-wallet/" + accountClientResponse.getAccountId(), WalletClientResponse.class);

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
            orderStatus.setOrderStatusId(Long.valueOf(OrderStatusCode.CREATED.getCode()));
            orderStatus.setStatus(String.valueOf(OrderStatusCode.CREATED));
            orderStatus.setOrderId(order.getId());
            orderStatusRepository.save(orderStatus);

            restTemplate.postForObject("http://localhost:9191/notification-service/api/notification/external/create-notification/" +
                            customerClientResponse.getCustomerId() +
                            "/" + orderStatus.getId() +
                            "/" + orderStatus.getStatus(),
                    null, Void.class);


            restTemplate.postForObject("http://localhost:9191/account-service/api/account/external/update-balance/" + accountClientResponse.getAccountId() + "/" + (productClientResponse.getProductPrice() * quantity), null, Void.class);

            CourierClientResponse courierClientResponse = restTemplate.postForObject("http://localhost:9191/courier-service/api/external/courier/set-courier/" + order.getId(),
                    null,
                    CourierClientResponse.class);

            restTemplate.postForObject("http://localhost:9191/inventory-service/api/inventory/set-quantity/" + productId + "/" + (inventoryClientResponse.getQuantity()-quantity), null, Void.class);

            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setQuantity(quantity);
            orderResponse.setCustomerClientResponse(customerClientResponse);
            orderResponse.setProductClientResponse(productClientResponse);
            orderResponse.setDateOfOrder(order.getDateOfOrder());
            orderResponse.setCourierClientResponse(courierClientResponse);
            orderResponse.setMessage("Order created successfully");

            orderResponses.add(orderResponse);


        });
        orderResponseList.setOrders(orderResponses);

        return orderResponseList;
    }

    @Override
    public OrderResponse getOrder(Long orderId) {
        if (ObjectUtils.isEmpty(orderId))
            throw new BusinessLogicException(BusinessLogicConstants.PR1001);

        Order order = orderRepository.findById(orderId).orElse(null);
        OrderDetails orderDetails = orderDetailsRepository.findByOrderId(orderId);

        if (ObjectUtils.isEmpty(order))
            throw new BusinessLogicException(BusinessLogicConstants.PR1004);

        ProductClientResponse productClientResponse = restTemplate.getForObject("http://localhost:9191/product-service/api/product/get-product/"+orderDetails.getProductId(), ProductClientResponse.class);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setProductClientResponse(productClientResponse);
        orderResponse.setDateOfOrder(order.getDateOfOrder());
        return orderResponse;
    }

    @Override
    public void updateOrderStatus(Long orderId, Long orderStatusId) {
        if (ObjectUtils.isEmpty(orderId))
            throw new BusinessLogicException(BusinessLogicConstants.PR1003);

        OrderStatus orderStatus = orderStatusRepository.findByOrderId(orderId);
        String varOrderStatus = String.valueOf(OrderStatusCode.getByCode(String.valueOf(orderStatusId)));
        orderStatus.setOrderStatusId(orderStatusId);
        orderStatus.setStatus(varOrderStatus);
        orderStatusRepository.save(orderStatus);
    }

    @Override
    public OrderResponseList createOrderWithVoice(SpeechToTextRequest request) {
        long customerId = request.getCustomerId();
        int quantity = request.getQuantity();
        log.info("customerId: {}, quantity: {}, ticketNo: {}", customerId, quantity, request.getTicketNos());

        if (ObjectUtils.isEmpty(customerId) || ObjectUtils.isEmpty(quantity) || ObjectUtils.isEmpty(request.getTicketNos())){
            throw new BusinessLogicException(BusinessLogicConstants.PR1001);
        }
        OrderResponseList orderResponseList = new OrderResponseList();
        List<OrderResponse> orderResponses = new ArrayList<>();
        request.getTicketNos().forEach(ticketNo -> {
            Long productId = restTemplate
                    .getForObject("http://localhost:9191/product-service/api/external/product/get-by-id-ticket-product/"+ticketNo, Long.class);

            CustomerClientResponse customerClientResponse = restTemplate
                    .getForObject("http://localhost:9191/customer-service/api/customer/get"+customerId, CustomerClientResponse.class);
            if (ObjectUtils.isEmpty(customerClientResponse))
                throw new BusinessLogicException(BusinessLogicConstants.PR1004);

            ProductClientResponse productClientResponse = restTemplate
                    .getForObject("http://localhost:9191/product-service/api/product/get-product/"+productId, ProductClientResponse.class);

            if (ObjectUtils.isEmpty(productClientResponse))
                throw new BusinessLogicException(BusinessLogicConstants.PR1004);

            InventoryClientResponse inventoryClientResponse = restTemplate
                    .getForObject("http://localhost:9191/inventory-service/api/inventory/get-inventory-by-product/"+productId, InventoryClientResponse.class);

            if (ObjectUtils.isEmpty(inventoryClientResponse))
                throw new BusinessLogicException(BusinessLogicConstants.PR1004);

            if (inventoryClientResponse.getQuantity() < quantity)
                throw new BusinessLogicException(BusinessLogicConstants.PR1006);

            AccountClientResponse accountClientResponse = restTemplate
                    .getForObject("http://localhost:9191/account-service/api/account/external/get-account/"+customerId, AccountClientResponse.class);

            if (ObjectUtils.isEmpty(accountClientResponse))
                throw new BusinessLogicException(BusinessLogicConstants.PR1004);

            WalletClientResponse walletClientResponse = restTemplate
                    .getForObject("http://localhost:9191/account-service/api/account/external/get-wallet/" + accountClientResponse.getAccountId(), WalletClientResponse.class);

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
            orderStatus.setOrderStatusId(Long.valueOf(OrderStatusCode.CREATED.getCode()));
            orderStatus.setStatus(String.valueOf(OrderStatusCode.CREATED));
            orderStatus.setOrderId(order.getId());
            orderStatusRepository.save(orderStatus);

            restTemplate.postForObject("http://localhost:9191/notification-service/api/notification/external/create-notification/" +
                            customerClientResponse.getCustomerId() +
                            "/" + orderStatus.getId() +
                            "/" + orderStatus.getStatus(),
                    null, Void.class);


            restTemplate
                    .postForObject("http://localhost:9191/account-service/api/account/external/update-balance/"
                            + accountClientResponse.getAccountId()
                            + "/" + (productClientResponse.getProductPrice() * quantity), null, Void.class);

            CourierClientResponse courierClientResponse = restTemplate
                    .postForObject("http://localhost:9191/courier-service/api/external/courier/set-courier/" + order.getId(),
                            null,
                            CourierClientResponse.class);

            restTemplate
                    .postForObject("http://localhost:9191/inventory-service/api/inventory/set-quantity/"
                            + productId + "/"
                            + (inventoryClientResponse.getQuantity()-quantity), null, Void.class);


            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrderId(order.getId());
            orderInfo.setQuantity(quantity);
            orderInfo.setPrice(productClientResponse.getProductPrice() * quantity);
            orderInfo.setProductName(productClientResponse.getProductName());
            orderInfo.setCustomerId(customerId);
            orderInfo.setProductId(productId);
            orderInfo.setCategory(productClientResponse.getProductCategory());
            orderInfo.setTicketNo(ticketNo);
            orderInfo.setStatus(OrderStatusCode.CREATED.toString());
            orderInfo.setDate(order.getDateOfOrder());
            orderInfo.setImgSrc(productClientResponse.getImageUrl());
            orderInfoRepository.save(orderInfo);


            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setQuantity(quantity);
            orderResponse.setCustomerClientResponse(customerClientResponse);
            orderResponse.setProductClientResponse(productClientResponse);
            orderResponse.setDateOfOrder(order.getDateOfOrder());
            orderResponse.setCourierClientResponse(courierClientResponse);
            orderResponse.setMessage("Order created successfully");

            orderResponses.add(orderResponse);
        });
        orderResponseList.setOrders(orderResponses);
        return orderResponseList;
    }

    @Override
    public OrderResponseList getOrders(Long customerId) {
        if (ObjectUtils.isEmpty(customerId))
            throw new BusinessLogicException(BusinessLogicConstants.PR1001);

        List<OrderInfo> orderInfos = orderInfoRepository.findAllByCustomerId(customerId);
        if (ObjectUtils.isEmpty(orderInfos))
            throw new BusinessLogicException(BusinessLogicConstants.PR1004);

        OrderResponseList orderResponseList = new OrderResponseList();
        List<OrderResponse> orderResponses = new ArrayList<>();
        orderInfos.forEach(orderInfo -> {
            ProductClientResponse productClientResponse = new ProductClientResponse();
            productClientResponse.setProductCategory(orderInfo.getProductName());
            productClientResponse.setProductName(orderInfo.getProductName());
            productClientResponse.setProductPrice(orderInfo.getPrice());
            productClientResponse.setProductTicketNo(orderInfo.getTicketNo());
            productClientResponse.setProductId(orderInfo.getProductId());
            productClientResponse.setImageUrl(orderInfo.getImgSrc());

            CustomerClientResponse customerClientResponse = new CustomerClientResponse();
            customerClientResponse.setCustomerId(orderInfo.getCustomerId());

            CourierClientResponse courierClientResponse = new CourierClientResponse();
            courierClientResponse.setPackageStatus(orderInfo.getStatus());

            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setOrderId(orderInfo.getOrderId());
            orderResponse.setQuantity(orderInfo.getQuantity());
            orderResponse.setCustomerClientResponse(customerClientResponse);
            orderResponse.setProductClientResponse(productClientResponse);
            orderResponse.setDateOfOrder(orderInfo.getDate());
            orderResponse.setCourierClientResponse(courierClientResponse);
            orderResponse.setMessage("Order created successfully");
            orderResponses.add(orderResponse);
        });

        orderResponseList.setOrders(orderResponses);
        return orderResponseList;

    }

    @Override
    public void deleteOrder(Long orderId) {
        if (ObjectUtils.isEmpty(orderId))
            throw new BusinessLogicException(BusinessLogicConstants.PR1001);

        Order order = orderRepository.findById(orderId).orElse(null);
        if (ObjectUtils.isEmpty(order))
            throw new BusinessLogicException(BusinessLogicConstants.PR1004);

        orderRepository.deleteById(orderId);

        OrderDetails orderDetails = orderDetailsRepository.findByOrderId(orderId);
        if (ObjectUtils.isEmpty(orderDetails))
            throw new BusinessLogicException(BusinessLogicConstants.PR1004);

        orderDetailsRepository.deleteById(orderDetails.getId());


        OrderStatus orderStatus = orderStatusRepository.findByOrderId(orderId);
        if (ObjectUtils.isEmpty(orderStatus))
            throw new BusinessLogicException(BusinessLogicConstants.PR1004);

        orderStatusRepository.deleteById(orderStatus.getId());


        OrderInfo orderInfo = orderInfoRepository.findByOrderId(orderId);
        if (ObjectUtils.isEmpty(orderInfo))
            throw new BusinessLogicException(BusinessLogicConstants.PR1004);

        orderInfoRepository.deleteById(orderInfo.getId());

    }
}
