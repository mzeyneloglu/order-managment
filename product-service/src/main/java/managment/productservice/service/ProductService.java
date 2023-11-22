package managment.productservice.service;

import managment.productservice.controller.request.ProductCreateRequest;
import managment.productservice.controller.response.ProductCreateResponse;
import managment.productservice.controller.response.ResultDiscountResponse;
import managment.productservice.exception.BusinessLogicException;
import managment.productservice.model.dto.ProductDTO;
import managment.productservice.model.dto.UpdateProductDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


public interface ProductService {

    ProductCreateResponse create(ProductCreateRequest productCreateRequest);

    ProductDTO get(Long productId);

    List<ProductDTO> getAll();

    UpdateProductDTO update(Long productId, ProductCreateRequest productCreateRequest);

    ResultDiscountResponse applyDiscount(Long productId);

    void delete(Long productId);

    void createAll(List<ProductCreateRequest> productCreateRequest);
}
