package gdg.hongik.mission.service;

import gdg.hongik.mission.dto.request.ProductBuyRequest;
import gdg.hongik.mission.dto.response.ProductResponse;
import gdg.hongik.mission.entity.Product;
import gdg.hongik.mission.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductUserService {

    private final ProductRepository productRepository;

    public ProductResponse getProduct(String name) {
        Product product = productRepository.findByName(name);

        if (product == null) {
            throw new RuntimeException("상품 없음");
        }

        return toResponse(product);
    }

    public List<ProductResponse> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public String buyProduct(List<ProductBuyRequest> orders) {
        int totalPrice = 0;
        StringBuilder result = new StringBuilder();

        for (ProductBuyRequest order : orders) {
            Product product = productRepository.findById(order.id())
                    .orElseThrow(() -> new RuntimeException("상품 없음"));

            if (product.getStock() < order.quantity()) {
                throw new RuntimeException("재고 부족");
            }

            product.removeStock(order.quantity());

            int price = product.getPrice() * order.quantity();
            totalPrice += price;

            result.append(product.getName())
                    .append(" / 구매 수량: ")
                    .append(order.quantity())
                    .append(" / 금액: ")
                    .append(price)
                    .append("\n");
        }

        result.append("총 구매 금액: ").append(totalPrice);

        return result.toString();
    }

    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
    }
}
