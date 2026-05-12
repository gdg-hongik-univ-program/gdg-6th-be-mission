package gdg.hongik.mission.DTO;

import java.util.List;

//요청 정보 : 상품 ID, 구매 수량 (여러 상품 가능)
public record ProductBuyRequest (
        List<OrderRequest> orderProducts
) {
    public record OrderRequest (
            Long id, int quantity
    ) {}
}
