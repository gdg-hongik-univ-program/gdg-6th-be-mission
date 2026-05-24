package gdg.hongik.mission.Controller;

import gdg.hongik.mission.DTO.*;
import gdg.hongik.mission.Service.ProductAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class ProductAdminController {

    private final ProductAdminService productAdminService;

    //상품 등록
    @PostMapping("/products")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductSaveRequest productSaveRequest) {

        return ResponseEntity.ok(productAdminService.addProduct(productSaveRequest));
    }

    //재고 추가
    @PatchMapping("/products/{id}/stock")
    public ResponseEntity<StockAddResponse> addStock(@PathVariable Long id, @Valid @RequestBody StockAddRequest stockAddRequest) {

        return ResponseEntity.ok(productAdminService.addStock(id, stockAddRequest));
    }

    //상품 삭제
    @DeleteMapping("/products")
    public ResponseEntity<ProductDeleteResponse> deleteProduct(@Valid @RequestBody ProductDeleteRequest productDeleteRequest) {

        return ResponseEntity.ok(productAdminService.deleteProduct(productDeleteRequest));
    }
}
