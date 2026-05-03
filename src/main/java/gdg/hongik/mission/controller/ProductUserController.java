package gdg.hongik.mission.controller;

import gdg.hongik.mission.Product;
import gdg.hongik.mission.ProductStore;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ProductUserController {

    // 상품명으로 조회
    @GetMapping("/products/{name}")
    public Product getProduct(@PathVariable String name) {

        for (Product product : ProductStore.products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        throw new RuntimeException("상품 없음");
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return ProductStore.products;
    }

    // 여러 상품 구매
    @PostMapping("/buy")
    public String buyProduct(@RequestBody List<Map<String, Integer>> orders) {

        int totalPrice = 0;
        StringBuilder result = new StringBuilder();

        for (Map<String, Integer> order : orders) {

            int id = order.get("id");
            int quantity = order.get("quantity");

            for (Product product : ProductStore.products) {

                if (product.getId() == id) {

                    if (product.getStock() < quantity) {
                        throw new RuntimeException("재고 부족");
                    }

                    product.setStock(product.getStock() - quantity);

                    int price = product.getPrice() * quantity;
                    totalPrice += price;

                    result.append(product.getName())
                            .append(" / 구매 수량: ")
                            .append(quantity)
                            .append(" / 금액: ")
                            .append(price)
                            .append("\n");
                }
            }
        }
        result.append("총 구맥 금액: ").append(totalPrice);

        return result.toString();
    }
}


