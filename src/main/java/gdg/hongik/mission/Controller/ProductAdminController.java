package gdg.hongik.mission.Controller;

import gdg.hongik.mission.Dto.DeleteProductsRequest;
import gdg.hongik.mission.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;



import gdg.hongik.mission.Dto.AddStockRequest;
import gdg.hongik.mission.Dto.CreateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductAdminController {
    private final ProductService productService;

    // 1. 상품 등록
    // 상품 등록 API
    @PostMapping
    public ResponseEntity<String> createProduct(@Valid @RequestBody CreateProductRequest request) {
        productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("상품이 등록되었습니다. ");
    }

    // 2. 재고 추가
    @PatchMapping("/stock")
    public ResponseEntity<String> addStock(@Valid @RequestBody AddStockRequest request) {
        productService.addStock(request);
        return ResponseEntity.ok("재고 추가가 완료되었습니다.");
    }

    // 3. 상품 삭제
    @DeleteMapping
    // <GetProductResponse>dto를 사용해서 외부에 보여주는 정보를 제한한다
    public ResponseEntity<String> deleteProducts(@Valid @RequestBody DeleteProductsRequest request) {
        productService.deleteProducts(request);
        return ResponseEntity.ok("상품 삭제가 완료되었습니다.");
    }
}
