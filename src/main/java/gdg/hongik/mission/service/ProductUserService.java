package gdg.hongik.mission.service;

import gdg.hongik.mission.entity.Product;
import gdg.hongik.mission.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductUserService {

    private final ProductRepository productRepository;

    // 상품명 조회
    public Product getProduct(String name) {

        Product product = productRepository.findByName(name);

        if (product == null) {
            throw new RuntimeException("상품 없음");
        }

        return product;
    }

    // 전체 상품 조회
    public List<Product> getProducts() {

        return productRepository.findAll();
    }

    // 상품 구매
    public String buyProduct(List<Map<String, Integer>> orders) {

        int totalPrice = 0;

        StringBuilder result = new StringBuilder();

        for (Map<String, Integer> order : orders) {

            Long id = order.get("id").longValue();
            int quantity = order.get("quantity");

            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("상품 없음"));

            if (product.getStock() < quantity) {
                throw new RuntimeException("재고 부족");
            }

            product.setStock(product.getStock() - quantity);

            productRepository.save(product);

            int price = product.getPrice() * quantity;

            totalPrice += price;

            result.append(product.getName())
                    .append(" / 구매 수량: ")
                    .append(quantity)
                    .append(" / 금액: ")
                    .append(price)
                    .append("\n");
        }

        result.append("총 구매 금액: ")
                .append(totalPrice);

        return result.toString();
    }
}

