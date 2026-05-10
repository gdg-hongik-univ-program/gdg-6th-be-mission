package gdg.hongik.mission.controller;
import gdg.hongik.mission.entity.Product;
import gdg.hongik.mission.ProductStore;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductUserController {

    // 1. 상품 조회
    @GetMapping("/products/{productId}")
    public Product getProduct(@PathVariable Long productId) {

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

        return product;
    }

    // 2. 상품 구매
    @PostMapping("/orders")
    public Map<String, Object> purchase(@RequestBody Map<String, Object> request) {

        List<Map<String, Object>> orderProducts =
                (List<Map<String, Object>>) request.get("orderProducts");

        int totalAmount = 0;
        List<Map<String, Object>> result = new ArrayList<>();

        for (int i = 0; i < orderProducts.size(); i++) {

            Map<String, Object> item = orderProducts.get(i);

            Long productId = Long.valueOf(item.get("productId").toString());
            int quantity = (int) item.get("quantity");

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

            if (product.getRemainQuantity() < quantity) {
                throw new RuntimeException("Not enough stock");
            }

            product.setRemainQuantity(product.getRemainQuantity() - quantity);

            int amount = product.getProductPrice() * quantity;
            totalAmount += amount;

            Map<String, Object> temp = new HashMap<>();
            temp.put("productName", product.getProductName());
            temp.put("quantity", quantity);
            temp.put("amount", amount);

            result.add(temp);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("orderId", "Ord" + System.currentTimeMillis()); // 매번 다른 주문Id를 생성하기 위해
        response.put("price", totalAmount);
        response.put("orderProducts", result);

        return response;
    }
}
