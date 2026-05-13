package gdg.hongik.mission.dto;


public record OrderProductResponse(
        Long productId,
        String productName,
        int quantity,  // 주문 수량
        int amount     // 주문한 비용
){}
