package managment.productservice.service.impl;

import lombok.RequiredArgsConstructor;
import managment.productservice.controller.request.ProductCreateRequest;
import managment.productservice.controller.response.ProductCreateResponse;
import managment.productservice.controller.response.ResultDiscountResponse;
import managment.productservice.model.dto.ProductDTO;
import managment.productservice.model.dto.UpdateProductDTO;
import managment.productservice.repository.ProductRepository;
import managment.productservice.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public ProductCreateResponse create(ProductCreateRequest productCreateRequest) {
        return null;
    }

    @Override
    public ProductDTO get(Long productId) {
        return null;
    }

    @Override
    public List<ProductDTO> getAll() {
        return null;
    }

    @Override
    public UpdateProductDTO update(Long productId, ProductCreateRequest productCreateRequest) {
        return null;
    }

    @Override
    public ResultDiscountResponse applyDiscount(Long productId, BigDecimal discount) {
        return null;
    }

    @Override
    public void delete(Long productId) {

    }
}
