package gdg.hongik.mission.Controller;

import gdg.hongik.mission.DTO.ProductBuyRequest;
import gdg.hongik.mission.DTO.ProductBuyResponse;
import gdg.hongik.mission.DTO.ProductFindResponse;
import gdg.hongik.mission.Service.ProductUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductUserController {

    private final ProductUserService productUserService;

    //상품 조회
    @GetMapping("/products")
    public ResponseEntity<ProductFindResponse> getProducts(@RequestParam String name) {

        ProductFindResponse productFindResponse
                = productUserService.findProductByName(name);

        return ResponseEntity.ok(productFindResponse);
    }

    //상품 구매
    @PostMapping("/orders")
    public ResponseEntity<ProductBuyResponse> buyProducts(@RequestBody ProductBuyRequest productBuyRequest) {

        ProductBuyResponse productBuyResponse = productUserService.buyProducts(productBuyRequest);

        return ResponseEntity.ok(productBuyResponse);
    }
}
