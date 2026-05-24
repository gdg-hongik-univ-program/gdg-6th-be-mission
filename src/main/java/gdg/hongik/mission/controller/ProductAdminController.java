package gdg.hongik.mission.controller;
import gdg.hongik.mission.dto.*;
import gdg.hongik.mission.service.ProductAdminService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class ProductAdminController {
    private final ProductAdminService productAdminService;

    public ProductAdminController(ProductAdminService productAdminService) {
        this.productAdminService = productAdminService;
    }

    // 1. 상품 등록
    @PostMapping("/admin/products")
    public ProductResponse createProduct(@Valid @RequestBody ProductCreateRequest request) {

        return productAdminService.createProduct(request);
    }

    // 2. 재고 추가
    @PatchMapping("/admin/products/{productName}")
    public AddStockResponse addStock(
            @PathVariable String productName,
            @Valid @RequestBody AddStockRequest request
    ) {

        return productAdminService.addStock(productName, request);
    }

    // 3. 상품 삭제
    @DeleteMapping("/admin/products/{productName}")
    public DeleteProductsResponse deleteProduct(@PathVariable @NotBlank(message = "삭제할 상품명은 필수입니다.") String productName) {

        return productAdminService.deleteProduct(productName);
    }
}
