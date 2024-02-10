package managment.productservice.service.impl;

import lombok.RequiredArgsConstructor;
import managment.productservice.controller.request.ProductCreateRequest;
import managment.productservice.controller.response.ProductCreateResponse;
import managment.productservice.controller.response.ResultDiscountResponse;
import managment.productservice.exception.BusinessLogicException;
import managment.productservice.model.Product;
import managment.productservice.model.dto.ProductDTO;
import managment.productservice.model.dto.UpdateProductDTO;
import managment.productservice.repository.ProductRepository;
import managment.productservice.service.ProductService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductCreateResponse create(ProductCreateRequest productCreateRequest) {
        if(Objects.isNull(productCreateRequest))
            throw new BusinessLogicException("REQUEST_CANNOT_BE_NULL");

        Product product = getProduct(productCreateRequest);
        ProductCreateResponse productCreateResponse = new ProductCreateResponse();
        productCreateResponse.toDto(product);
        productCreateResponse.setMessage("PRODUCT_CREATED");

        productRepository.save(product);
        return productCreateResponse;

    }
    @Override
    public ProductDTO get(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if(ObjectUtils.isEmpty(product) )
            throw new BusinessLogicException("PRODUCT_NOT_FOUND");

        ProductDTO productDTO = new ProductDTO();
        productDTO.toDto(product);
        return productDTO;
    }

    @Override
    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();
        if (ObjectUtils.isEmpty(products))
            throw new BusinessLogicException("PRODUCT_NOT_FOUND");

        return products.stream().map(product -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.toDto(product);
            return productDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public UpdateProductDTO update(Long productId, ProductCreateRequest productCreateRequest) {
        if (Objects.isNull(productCreateRequest))
            throw new BusinessLogicException("REQUEST_CANNOT_BE_NULL");

        Product product = productRepository.findById(productId).orElse(null);
        if (ObjectUtils.isEmpty(product))
            throw new BusinessLogicException("PRODUCT_NOT_FOUND");

        product.setName(productCreateRequest.getProductName());
        product.setDescription(productCreateRequest.getProductDescription());
        product.setCategory(productCreateRequest.getProductCategory());
        product.setPrice(productCreateRequest.getProductPrice());
        product.setDiscount(productCreateRequest.getProductDiscount());

        UpdateProductDTO updateProductDTO = new UpdateProductDTO();
        updateProductDTO.toDto(product);
        updateProductDTO.setMessage("PRODUCT_UPDATED");

        productRepository.save(product);
        return updateProductDTO;

    }

    @Override
    public ResultDiscountResponse applyDiscount(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (ObjectUtils.isEmpty(product))
            throw new BusinessLogicException("PRODUCT_NOT_FOUND");

        product.setPrice((product.getPrice()) - (product.getPrice() * product.getDiscount()));

        ResultDiscountResponse resultDiscountResponse = new ResultDiscountResponse();
        resultDiscountResponse.setNewPrice(product.getPrice());
        resultDiscountResponse.setMessage("APPLIED_DISCOUNT_TO_PRODUCT");
        productRepository.save(product);
        return resultDiscountResponse;

    }

    @Override
    public void delete(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (ObjectUtils.isEmpty(product))
            throw new BusinessLogicException("PRODUCT_NOT_FOUND");

        productRepository.delete(product);

    }

    @Override
    public void createAll(List<ProductCreateRequest> productCreateRequest) {
        if (Objects.isNull(productCreateRequest))
            throw new BusinessLogicException("REQUEST_CANNOT_BE_NULL");

        productRepository.saveAll(productCreateRequest.stream().map(this::getProduct).collect(Collectors.toList()));
    }

    private Product getProduct(ProductCreateRequest productCreateRequest1) {
        Product product = new Product();
        product.setName(productCreateRequest1.getProductName());
        product.setDescription(productCreateRequest1.getProductDescription());
        product.setCategory(productCreateRequest1.getProductCategory());
        product.setPrice(productCreateRequest1.getProductPrice());
        product.setDiscount(productCreateRequest1.getProductDiscount());
        return product;
    }
}
