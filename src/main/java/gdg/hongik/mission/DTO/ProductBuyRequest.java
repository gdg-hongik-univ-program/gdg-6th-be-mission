package gdg.hongik.mission.DTO;

import gdg.hongik.mission.common.Message;
import jakarta.validation.constraints.*;

import java.util.List;

//요청 정보 : 상품 ID, 구매 수량 (여러 상품 가능)
public record ProductBuyRequest (

        @NotEmpty(message = Message.LIST_NOT_EMPTY)
        List<OrderRequest> orderProducts
) {
    public record OrderRequest (

            @Positive(message = Message.ID_NOT_NEGATIVE)
            Long id,

            @Positive(message = Message.QUANTITY_OUT_OF_RANGE)
            int quantity
    ) {}
}
