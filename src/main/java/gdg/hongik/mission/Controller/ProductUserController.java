package gdg.hongik.mission.Controller;

import gdg.hongik.mission.Dto.PurchaseListRequest;
import gdg.hongik.mission.Service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;


import gdg.hongik.mission.Dto.GetProductResponse;
import gdg.hongik.mission.Dto.PurchaseRequest;
import gdg.hongik.mission.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductUserController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    // 상품 조회
    @GetMapping
    public ResponseEntity<GetProductResponse> getProduct(@RequestParam String name) {

        GetProductResponse response = productService.getProduct(name);
        return ResponseEntity.ok(response);
    }

    // 상품 구매
    @Operation(summary = "상품 구매", description = "상품 리스트를 받아 구매를 처리합니다. ")
    @PostMapping("/purchase")
    public ResponseEntity<String> purchaseProducts(@Valid @RequestBody PurchaseListRequest wrapperRequests) {
        // 복잡한 재고 확인, 계산, 문자열 생성은 모두 서비스가 처리합니다.
        String result = productService.purchaseProducts(wrapperRequests.requests());

        return ResponseEntity.ok(result);
    }
}
