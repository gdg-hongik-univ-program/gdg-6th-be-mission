package gdg.hongik.mission.Controller;

import gdg.hongik.mission.DTO.*;
import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.Service.ProductAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ProductAdminController {

    private final ProductAdminService productAdminService;

    @Autowired
    public ProductAdminController(ProductAdminService productAdminService) {
        this.productAdminService = productAdminService;
    }

    //상품 등록
    @PostMapping("/products")
    public ResponseEntity<Void> addProduct(@RequestBody ProductSaveRequest productSaveRequest) {

        productAdminService.addProduct(productSaveRequest);

        return ResponseEntity.ok().build();
    }

    //재고 추가
    @PatchMapping("/products/{id}/stock")
    public ResponseEntity<?> addStock(@PathVariable Long id, @RequestBody StockAddRequest stockAddRequest) {

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
