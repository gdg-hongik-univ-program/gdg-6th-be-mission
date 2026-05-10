package gdg.hongik.mission.Controller;

import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.Service.ProductUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ProductUserController {

    private final ProductUserService productUserService;

    @Autowired
    public ProductUserController(ProductUserService productUserService) {
        this.productUserService = productUserService;
    }

    //상품 조회
    @GetMapping("/products")
    public ResponseEntity<Product> getProducts(@RequestParam(required = false) String name) {

        Product product = productUserService.findProductByName(name);

        return ResponseEntity.ok(product);
    }

    //상품 구매
    @PostMapping("/orders")
    public ResponseEntity<Void> buyProducts(@RequestBody Map<String, List<Map<String, Object>>> request) {

        productUserService.editProduct(request);

        return ResponseEntity.status(201).build();
    }
}
