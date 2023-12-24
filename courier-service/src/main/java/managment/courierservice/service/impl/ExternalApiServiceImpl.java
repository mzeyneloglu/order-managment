package managment.courierservice.service.impl;

import jakarta.transaction.Transactional;
import managment.courierservice.constants.CourierStatusCode;
import managment.courierservice.controller.response.CourierClientResponse;
import managment.courierservice.exception.BusinessLogicException;
import managment.courierservice.model.Courier;
import managment.courierservice.model.OrderInformation;
import managment.courierservice.repository.CourierRepository;
import managment.courierservice.repository.OrderInformationRepository;
import managment.courierservice.service.ExternalApiService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class ExternalApiServiceImpl implements ExternalApiService {
    private final OrderInformationRepository orderInformationRepository;
    private final CourierRepository courierRepository;

    public ExternalApiServiceImpl(OrderInformationRepository orderInformationRepository, CourierRepository courierRepository) {
        this.orderInformationRepository = orderInformationRepository;
        this.courierRepository = courierRepository;
    }
    @Override
    public CourierClientResponse setCourier(Long orderId) {
        if (ObjectUtils.isEmpty(orderId))
            throw new BusinessLogicException("ORDER_ID_IS_EMPTY");

        List<Courier> courierList = courierRepository.findAll();
        List<Courier> freeCourierList = courierList.stream().map(courier -> courier.getCourierStatus().equals("FREE") ? courier : null).toList();
        if (ObjectUtils.isEmpty(freeCourierList))
            throw new BusinessLogicException("NO_FREE_COURIER");

        Courier tempCourier = freeCourierList
                .stream()
                .min(Comparator.comparing(Courier::getCrPackageNumber))
                .orElseThrow(() -> new BusinessLogicException("COURIER_NOT_FOUND"));

        OrderInformation orderInformation = new OrderInformation();
        orderInformation.setCourierId(tempCourier.getId());
        orderInformation.setOrderId(orderId);
        orderInformation.setStatusCode(String.valueOf(CourierStatusCode.GETTING_READY));
        orderInformationRepository.save(orderInformation);

        List<Courier> busyCourierList = courierList.stream().map(courier -> courier.getCourierStatus().equals("BUSY") ? courier : null).toList();
        if (ObjectUtils.isEmpty(freeCourierList))
            throw new BusinessLogicException("NO_BUSY_COURIER");

        busyCourierList.forEach(courier -> {
            courier.setCourierStatus("FREE");
            courierRepository.save(courier);
        });

        tempCourier.setCrPackageNumber(tempCourier.getCrPackageNumber() + 1);
        tempCourier.setCourierStatus("BUSY");
        courierRepository.save(tempCourier);
        CourierClientResponse response = new CourierClientResponse();
        response.setPackageStatus(String.valueOf(CourierStatusCode.GETTING_READY));
        return response;
    }
}
