package gdg.hongik.mission.Controller;

import gdg.hongik.mission.DTO.*;
import gdg.hongik.mission.Service.ProductAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class ProductAdminController {

    private final ProductAdminService productAdminService;

    //상품 등록
    @PostMapping("/products")
    public ResponseEntity<Void> addProduct(@RequestBody ProductSaveRequest productSaveRequest) {

        productAdminService.addProduct(productSaveRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //재고 추가
    @PatchMapping("/products/{id}/stock")
    public ResponseEntity<StockAddResponse> addStock(@PathVariable Long id, @RequestBody StockAddRequest stockAddRequest) {

        StockAddResponse stockAddResponse = productAdminService.addStock(id, stockAddRequest);

        return ResponseEntity.ok(stockAddResponse);
    }

    //상품 삭제
    @DeleteMapping("/products")
    public ResponseEntity<ProductDeleteResponse> deleteProduct(@RequestBody ProductDeleteRequest productDeleteRequest) {

        ProductDeleteResponse productDeleteResponse = productAdminService.deleteProduct(productDeleteRequest);

        return ResponseEntity.ok(productDeleteResponse);
    }
}
