package gdg.hongik.mission.controller;

import gdg.hongik.mission.entity.Product;
import gdg.hongik.mission.service.ProductAdminService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductAdminController {

    private final ProductAdminService productAdminService;

    @PostMapping("/admin/product")
    public Product createProduct(@RequestBody Product product) {

        return productAdminService.createProduct(product);
    }

    @PatchMapping("/admin/product/{id}/stock")
    public Product addStock(@PathVariable Long id, @RequestParam int quantity){

        return productAdminService.addStock(id, quantity);
    }

    @DeleteMapping("/admin/products")
    public List<Product> deleteProducts(@RequestBody List<Long> ids) {

        return productAdminService.deleteProducts(ids);
    }
}
