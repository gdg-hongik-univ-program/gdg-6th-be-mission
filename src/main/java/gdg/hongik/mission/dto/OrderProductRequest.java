package gdg.hongik.mission.dto;


// 상품 주문 요청 정보를 전달하는 dto
public record OrderProductRequest(Long productId, int quantity)
{}
