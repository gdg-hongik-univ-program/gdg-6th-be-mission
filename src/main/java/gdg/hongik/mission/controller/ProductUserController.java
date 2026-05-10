package gdg.hongik.mission.controller;
import org.springframework.web.bind.annotation.*;
import gdg.hongik.mission.service.ProductUserService;
import java.util.*;

@RestController
public class ProductUserController {
    private final ProductUserService productUserService;

    public ProductUserController(ProductUserService productUserService) {
        this.productUserService = productUserService;
    }

    // 상품 구매 요청
    @PostMapping("/orders")
    public Map<String, Object> purchase(
            @RequestBody Map<String, Object> request
    ) {

        return productUserService.purchase(request);
    }
}
