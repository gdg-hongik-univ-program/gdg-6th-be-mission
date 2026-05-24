package gdg.hongik.mission.service;
import gdg.hongik.mission.dto.*;
import gdg.hongik.mission.entity.Product;
import gdg.hongik.mission.exception.OutOfStockException;
import gdg.hongik.mission.exception.ProductNotFoundException;
import gdg.hongik.mission.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductUserService {

    private final ProductRepository productRepository;

    public ProductUserService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public ProductResponse getProduct(String name) {
        // 1. 이름으로 DB에서 엔티티 조회 (없으면 에러)
        Product product = productRepository.findByProductName(name)
                .orElseThrow(ProductNotFoundException::new);
        // 2. 조회된 Product 엔티티의 데이터를 ProductResponse 레코드에 담아서 반환
        return new ProductResponse(
                product.getProductId(),
                product.getProductName(),
                product.getProductPrice(),
                product.getRemainQuantity()
        );
    }

    @Transactional
    public OrderResponse purchase(OrderRequest request) {
        List<OrderProductRequest> orderItems = request.orderProducts();

        // 주문할 상품들의 ID만 뽑아서 리스트로 만듦
        List<Long> productIds = orderItems.stream()
                .map(OrderProductRequest::productId)
                .toList();

        // findAll() 대신 필요한 상품만 일괄 쿼리로 조회 )
        List<Product> foundProducts = productRepository.findAllById(productIds);

        // 💡 2중 for문을 없애기 위해 상품 ID를 Key로 갖는 Map으로 변환 (O(1) 속도로 탐색 가능)
        Map<Long, Product> productMap = foundProducts.stream()
                .collect(Collectors.toMap(Product::getProductId, product -> product));

        int totalAmount = 0;
        List<OrderProductResponse> resultResponses = new ArrayList<>();

        // 향상된 for문 사용으로 가독성 향상
        for (OrderProductRequest item : orderItems) {
            // Map에서 상품을 즉시 찾음 (없으면 404 예외)
            Product product = Optional.ofNullable(productMap.get(item.productId()))
                    .orElseThrow(ProductNotFoundException::new);

            // 💡 재고 차감 및 금액 계산 비즈니스 로직을 별도 함수로 분리
            int itemAmount = calculateAndDecreaseStock(product, item.quantity());
            totalAmount += itemAmount;

            resultResponses.add(new OrderProductResponse(
                    product.getProductId(),
                    product.getProductName(),
                    item.quantity(),
                    itemAmount
            ));
        }

        // 중복 위험이 없는 안전한 UUID 기반 주문번호 생성
        String orderNo = "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        return new OrderResponse(orderNo, totalAmount, resultResponses);
    }


    private int calculateAndDecreaseStock(Product product, int orderQuantity) {
        if (product.getRemainQuantity() < orderQuantity) {
            throw new OutOfStockException(); // 우리가 만든 재고 부족 커스텀 예외!
        }

        // JPA의 Dirty Checking(변경 감지) 덕분에 save()를 따로 안 호출해도 트랜잭션 종료 시 DB에 반영됩니다.
        product.setRemainQuantity(product.getRemainQuantity() - orderQuantity);

        return product.getProductPrice() * orderQuantity;
    }
}
