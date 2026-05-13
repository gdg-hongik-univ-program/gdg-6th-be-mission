package gdg.hongik.mission.service;
import gdg.hongik.mission.dto.OrderProductRequest;
import gdg.hongik.mission.dto.OrderProductResponse;
import gdg.hongik.mission.dto.OrderRequest;
import gdg.hongik.mission.dto.OrderResponse;
import gdg.hongik.mission.entity.Product;
import gdg.hongik.mission.repository.ProductRepository;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class ProductUserService {

    private final ProductRepository productRepository;

    public ProductUserService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public OrderResponse purchase(OrderRequest request) {

        List<OrderProductRequest> orderProducts =
                request.orderProducts();

        int totalAmount = 0;  // 총 구매 금액
        List<OrderProductResponse> result = new ArrayList<>();

        List<Product> products = productRepository.findAll();

        for (int i = 0; i < orderProducts.size(); i++) {

            OrderProductRequest item = orderProducts.get(i);

            Long productId = item.productId();
            int quantity = item.quantity();

            Product product = null;

            for (Product p : products) {
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
            productRepository.save(product);

            int amount = product.getProductPrice() * quantity;
            totalAmount += amount;

            OrderProductResponse temp = new OrderProductResponse(
                   product.getProductId(),
                    product.getProductName(),
                    quantity,
                    amount
            );

            result.add(temp);
        }

        return new OrderResponse(
                "ord"+System.currentTimeMillis(),
                totalAmount,
                result
        );
    }
}
