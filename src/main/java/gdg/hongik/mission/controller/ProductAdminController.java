package gdg.hongik.mission.controller;

import gdg.hongik.mission.dto.request.ProductCreateRequest;
import gdg.hongik.mission.dto.request.StockUpdateRequest;
import gdg.hongik.mission.dto.response.ProductResponse;
import gdg.hongik.mission.service.ProductAdminService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@RestController
@CrossOrigin(origins = "*")
@Validated
@RequiredArgsConstructor
@RequestMapping("/admin/products")
public class ProductAdminController {

    private final ProductAdminService productAdminService;

    // 상품 등록
    @PostMapping
    public ProductResponse createProduct(
            @Valid @RequestBody ProductCreateRequest request
    ) {

        return productAdminService.createProduct(request);
    }

    // 재고 추가
    @PatchMapping("/{id}/stock")
    public ProductResponse addStock(
            @PathVariable Long id,
            @Valid @RequestBody StockUpdateRequest request
    ) {

        return productAdminService.addStock(id, request);
    }

    // 여러 상품 삭제
    @DeleteMapping
    public List<ProductResponse> deleteProducts(
            @RequestBody List<Long> ids
    ) {

        return productAdminService.deleteProducts(ids);
    }
}