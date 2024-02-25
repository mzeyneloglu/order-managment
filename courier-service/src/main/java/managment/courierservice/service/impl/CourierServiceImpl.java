package managment.courierservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import managment.courierservice.controller.request.CourierRequest;
import managment.courierservice.controller.response.CourierResponse;
import managment.courierservice.exception.BusinessLogicConstants;
import managment.courierservice.exception.BusinessLogicException;
import managment.courierservice.model.Courier;
import managment.courierservice.repository.CourierRepository;
import managment.courierservice.service.CourierService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Transactional
@RequiredArgsConstructor
public class CourierServiceImpl implements CourierService {
    private final CourierRepository courierRepository;
    @Override
    public CourierResponse create(CourierRequest request) {
        if (ObjectUtils.isEmpty(request))
            throw new BusinessLogicException(BusinessLogicConstants.PR1001);

        isCheckCourier(request);

        Courier courier = new Courier();
        courier.setCourierCompany(request.getCourierCompany());
        courier.setCourierName(request.getCourierName());
        courier.setCourierPhone(request.getCourierPhone());
        courier.setCourierSurname(request.getCourierSurname());
        courier.setCourierStatus(request.getCourierStatus());
        courier.setCrPackageNumber(request.getCrPackageNumber());
        courierRepository.save(courier);

        return getCourierResponse(courier);

    }

    private CourierResponse getCourierResponse(Courier courier) {
        CourierResponse response = new CourierResponse();
        response.setCourierCompany(courier.getCourierCompany());
        response.setCourierName(courier.getCourierName());
        response.setCourierPhone(courier.getCourierPhone());
        response.setCourierSurname(courier.getCourierSurname());
        return response;
    }
    private void isCheckCourier(CourierRequest request) {
        boolean checkCourier = courierRepository.findAll().stream().anyMatch(courier -> (
                courier.getCourierPhone().equals(request.getCourierPhone())
                && courier.getCourierName().equals(request.getCourierName())
                && courier.getCourierSurname().equals(request.getCourierSurname())));
        if (checkCourier)
            throw new BusinessLogicException(BusinessLogicConstants.PR1005);
    }


}
