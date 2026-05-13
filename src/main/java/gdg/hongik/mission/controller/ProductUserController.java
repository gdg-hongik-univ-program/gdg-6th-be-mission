package gdg.hongik.mission.controller;
import gdg.hongik.mission.dto.OrderRequest;
import gdg.hongik.mission.dto.OrderResponse;
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
    public OrderResponse purchase(
            @RequestBody OrderRequest request
    ) {

        return productUserService.purchase(request);
    }
}
