package gdg.hongik.mission.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//요청 정보 : 상품 ID, 구매 수량 (여러 상품 가능)
@Getter @Setter
@NoArgsConstructor
public class ProductBuyRequest {

    public List<OrderRequest> orderProducts = new ArrayList<>();

    @Getter @Setter
    @NoArgsConstructor
    public static class OrderRequest {
        Long id;
        int quantity;

        public OrderRequest(Long id, int quantity) {
            this.id = id;
            this.quantity = quantity;
            //orderRequestList.add(this);
        }
    }

}
