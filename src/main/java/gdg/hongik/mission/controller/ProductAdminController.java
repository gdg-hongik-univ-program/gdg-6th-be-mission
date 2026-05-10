package gdg.hongik.mission.controller;
import gdg.hongik.mission.entity.Product;
import gdg.hongik.mission.ProductStore;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class ProductAdminController {

    // 1. 상품 등록
    @PostMapping("/products")
    public Object createProduct(@RequestBody Map<String, Object> request) {

        String productName = (String) request.get("productName");

        // 중복 검사
        for (Product p : ProductStore.products) {
            if (p.getProductName().equals(productName)) {
                Map<String, Object> error = new HashMap<>();
                error.put("error", "상품이 이미 존재합니다");
                return error;
            }
        }

        Product product = new Product();
        product.setProductName(productName);
        product.setProductPrice((int) request.get("productPrice"));
        product.setRemainQuantity((int) request.get("remainQuantity"));

        ProductStore.products.add(product);

        return product;
    }

    // 2. 재고 추가
    @PatchMapping("/products/{productId}")
    public Map<String, Object> addStock(
            @PathVariable Long productId,
            @RequestBody Map<String, Object> request
    ) {

        int addQuantity = (int) request.get("addQuantity");

        Product product = null;

        for (Product p : ProductStore.products) {
            if (p.getProductId().equals(productId)) {
                product = p;
                break;
            }
        }

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        product.setRemainQuantity(product.getRemainQuantity() + addQuantity);

        Map<String, Object> response = new HashMap<>();
        response.put("productName", product.getProductName());
        response.put("remainQuantity", product.getRemainQuantity());

        return response;
    }

    // 3. 상품 삭제
    @DeleteMapping("/products")
    public Map<String, Object> deleteProducts(@RequestBody Map<String, Object> request) {

        List<Long> productIds = (List<Long>) request.get("productIds");

        for (int i = 0; i < ProductStore.products.size(); i++) {
            Product p = ProductStore.products.get(i);

            if (productIds.contains(p.getProductId())) {
                ProductStore.products.remove(i);
                i--; // 인덱스 보정
            }
        }

        List<Map<String, Object>> result = new ArrayList<>();

        for (Product p : ProductStore.products) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("productName", p.getProductName());
            temp.put("remainQuantity", p.getRemainQuantity());
            result.add(temp);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("products", result);

        return response;
    }
}
