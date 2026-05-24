package gdg.hongik.mission.Controller;

import gdg.hongik.mission.DTO.ProductBuyRequest;
import gdg.hongik.mission.DTO.ProductBuyResponse;
import gdg.hongik.mission.DTO.ProductDTO;
import gdg.hongik.mission.Service.ProductUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class ProductUserController {

    private final ProductUserService productUserService;

    //상품 조회
    @GetMapping("/products")
    public ResponseEntity<ProductDTO> getProducts(@RequestParam String name) {

        return ResponseEntity.ok(productUserService.findProductByName(name));
    }

    //상품 구매
    @PostMapping("/orders")
    public ResponseEntity<ProductBuyResponse> buyProducts(@Valid @RequestBody ProductBuyRequest productBuyRequest) {

        return ResponseEntity.ok(productUserService.buyProducts(productBuyRequest));
    }
}
