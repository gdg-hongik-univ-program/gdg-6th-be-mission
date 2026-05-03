package gdg.hongik.mission.controller;

import gdg.hongik.mission.Product;
import gdg.hongik.mission.ProductStore;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductAdminController {

    // 상품 등록
    @PostMapping("/admin/product")
    public Product createProduct(@RequestBody Product product) {

        for (Product existingProduct : ProductStore.products) {
            if (existingProduct.getName().equals(product.getName())) {
                throw new RuntimeException("이미 존재하는 상품명");
            }
        }

        product.setId(ProductStore.sequence++);
        ProductStore.products.add(product);

        return product;
    }

    // 재고 추가
    @PatchMapping("/admin/product/{id}/stock")
    public Product addStock(@PathVariable Long id, @RequestParam int quantity) {

        for (Product product : ProductStore.products) {
            if (product.getId().equals(id)) {

                product.setStock(product.getStock() + quantity);

                return product;
            }
        }

        throw new RuntimeException("상품 없음");
    }

    // 여러 상품 삭제
    @DeleteMapping("/admin/products")
    public List<Product> deleteProducts(@RequestBody List<Long> ids) {

        ProductStore.products.removeIf(product -> ids.contains(product.getId()));

        return ProductStore.products;
    }
}


