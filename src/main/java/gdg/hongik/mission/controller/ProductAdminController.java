package gdg.hongik.mission.controller;
import gdg.hongik.mission.entity.Product;
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
    public Object createProduct(@RequestBody Map<String, Object> request) {

        return productAdminService.createProduct(request);
    }

    // 2. 재고 추가
    @PatchMapping("/products/{productId}")
    public Map<String, Object> addStock(
            @PathVariable Long productId,
            @RequestBody Map<String, Object> request
    ) {

        return productAdminService.addStock(productId, request);
    }

    // 3. 상품 삭제
    @DeleteMapping("/products")
    public Map<String, Object> deleteProducts(@RequestBody Map<String, Object> request) {

        {

            return productAdminService.deleteProducts(request);
        }
    }
}
