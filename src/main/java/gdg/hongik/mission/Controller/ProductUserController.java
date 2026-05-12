package gdg.hongik.mission.Controller;

import gdg.hongik.mission.DTO.ProductBuyRequest;
import gdg.hongik.mission.DTO.ProductBuyResponse;
import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.Service.ProductUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProductUserController {

    private final ProductUserService productUserService;

    //상품 조회
    @GetMapping("/products")
    public ResponseEntity<Product> getProducts(@RequestParam(required = false) String name) {

        Product product = productUserService.findProductByName(name);

        return ResponseEntity.ok(product);
    }

    //상품 구매
    @PostMapping("/orders")
    public ResponseEntity<ProductBuyResponse> buyProducts(@RequestBody ProductBuyRequest productBuyRequest) {

        ProductBuyResponse productBuyResponse = productUserService.editProduct(productBuyRequest);

        return ResponseEntity.ok(productBuyResponse);
    }
}
