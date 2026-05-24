package gdg.hongik.mission.controller;

import gdg.hongik.mission.dto.request.ProductBuyRequest;
import gdg.hongik.mission.dto.response.ProductResponse;
import gdg.hongik.mission.service.ProductUserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@RestController
@Validated
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductUserController {

    private final ProductUserService productUserService;

    // 상품 조회
    @GetMapping("/{name}")
    public ProductResponse getProduct(
            @PathVariable String name
    ) {

        return productUserService.getProduct(name);
    }

    // 전체 상품 조회
    @GetMapping
    public List<ProductResponse> getProducts() {

        return productUserService.getProducts();
    }

    // 상품 구매
    @PostMapping("/buy")
    public String buyProduct(
            @RequestBody List<@Valid ProductBuyRequest> orders
    ) {

        return productUserService.buyProduct(orders);
    }

}