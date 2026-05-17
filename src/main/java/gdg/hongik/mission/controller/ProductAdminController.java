package gdg.hongik.mission.controller;
import gdg.hongik.mission.dto.*;
import gdg.hongik.mission.service.ProductAdminService;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class ProductAdminController {
    private final ProductAdminService productAdminService;

    public ProductAdminController(ProductAdminService productAdminService) {
        this.productAdminService = productAdminService;
    }

    // 1. 상품 등록
    @PostMapping("/products")
    public ProductResponse createProduct(@RequestBody ProductCreateRequest request) {

        return productAdminService.createProduct(request);
    }

    // 2. 재고 추가
    @PatchMapping("/products/{productId}")
    public AddStockResponse addStock(
            @PathVariable Long productId,
            @RequestBody AddStockRequest request
    ) {

        return productAdminService.addStock(productId, request);
    }

    // 3. 상품 삭제
    @DeleteMapping("/products")
    public DeleteProductsResponse deleteProducts(@RequestBody DeleteProductRequest request) {
        {
            return productAdminService.deleteProducts(request);
        }
    }
}
