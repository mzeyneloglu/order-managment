package managment.productservice.controller;

import lombok.RequiredArgsConstructor;
import managment.productservice.constant.ApiEndpoints;
import managment.productservice.controller.request.ProductCreateRequest;
import managment.productservice.controller.response.ProductCreateResponse;
import managment.productservice.controller.response.ResultDiscountResponse;
import managment.productservice.model.dto.ProductDTO;
import managment.productservice.model.dto.UpdateProductDTO;
import managment.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = ApiEndpoints.END_POINT)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("create-product")
    // @ApiOperation(value = "create product")
    public ProductCreateResponse create(@RequestBody ProductCreateRequest productCreateRequest) {
        return productService.create(productCreateRequest);
    }
    @PostMapping("create-many-products")
    public void createMany(@RequestBody List<ProductCreateRequest> productCreateRequest){
        productService.createAll(productCreateRequest);
    }

    @GetMapping("get-product/{productId}")
    // @ApiOperation(value = "get product")
    public ProductDTO get(@PathVariable Long productId){
        return productService.get(productId);
    }
    @GetMapping("list-of-products")
    // @ApiOperation(value = "list of products")
    public List<ProductDTO> getAll(){
        return productService.getAll();
    }

    @PostMapping("update-product")
    // @ApiOperation(value = "update product")
    public UpdateProductDTO update(@RequestParam Long productId,
                                   @RequestBody ProductCreateRequest productCreateRequest){
        return productService.update(productId, productCreateRequest);
    }

    @PostMapping("apply-discount")
    // @ApiOperation(value = "apply discount")
    public ResultDiscountResponse applyDiscount(@RequestParam Long productId){
        return productService.applyDiscount(productId);
    }

    @DeleteMapping("delete-product")
    // @ApiOperation(value = "delete product")
    public void delete(@RequestParam Long productId){
        productService.delete(productId);
    }

}
