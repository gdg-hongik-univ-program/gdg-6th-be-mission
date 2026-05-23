package gdg.hongik.mission.controller;

import gdg.hongik.mission.dto.request.ProductCreateRequest;
import gdg.hongik.mission.dto.request.StockUpdateRequest;
import gdg.hongik.mission.dto.response.ProductResponse;
import gdg.hongik.mission.service.ProductAdminService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/admin/products")
public class ProductAdminController {

    private final ProductAdminService productAdminService;

    // 상품 등록
    @PostMapping
    public ProductResponse createProduct(
            @RequestBody ProductCreateRequest request
    ) {
        System.out.println("post 요청 들어옴");

        return productAdminService.createProduct(request);
    }

    // 재고 추가
    @PatchMapping("/{id}/stock")
    public ProductResponse addStock(
            @PathVariable Long id,
            @RequestBody StockUpdateRequest request
    ) {
        System.out.println("patch 요청 들어옴");

        return productAdminService.addStock(id, request);
    }

    // 여러 상품 삭제
    @DeleteMapping
    public List<ProductResponse> deleteProducts(
            @RequestBody List<Long> ids
    ) {
        System.out.println("delete 요청 들어옴");

        return productAdminService.deleteProducts(ids);
    }
}