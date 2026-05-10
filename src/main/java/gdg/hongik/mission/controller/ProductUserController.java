package gdg.hongik.mission.controller;

import gdg.hongik.mission.entity.Product;
import gdg.hongik.mission.service.ProductUserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation. *;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProductUserController {

    private final ProductUserService productUserService;

    @GetMapping("/products/{name}")
    public Product getProduct(@PathVariable String name) {

        return productUserService.getProduct(name);
    }

    @GetMapping("/products")
    public List<Product> getProducts() {

        return productUserService.getProducts();
    }

    @PostMapping("/buy")
    public String buyProduct(@RequestBody List<Map<String, Integer>> orders) {

        return productUserService.buyProduct(orders);
    }
}
