package managment.productservice.controller;

import com.ctc.wstx.util.StringUtil;
import lombok.RequiredArgsConstructor;
import managment.productservice.constant.ApiEndpoints;
import managment.productservice.exception.BusinessLogicConstants;
import managment.productservice.exception.BusinessLogicException;
import managment.productservice.model.Product;
import managment.productservice.model.dto.ProductDTO;
import managment.productservice.repository.ProductRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiEndpoints.EXTERNAL_API)
@RequiredArgsConstructor
public class ExternalController {
    private final ProductRepository productRepository;

    @GetMapping("/get-by-id-ticket-product/{ticketNo}")
    public Long getByIdProduct(@PathVariable String ticketNo){
        if (StringUtils.isEmpty(ticketNo))
            throw new BusinessLogicException(BusinessLogicConstants.PR1004);

       return productRepository.getProductIdByTicketNo(ticketNo);
    }
}
