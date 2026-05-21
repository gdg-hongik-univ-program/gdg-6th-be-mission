package gdg.hongik.mission.DTO;

import gdg.hongik.mission.common.message.ErrorMessage;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
// 상품 구매 요청
public class PurchaseProductRequest {

    @NotNull(message = ErrorMessage.PRODUCT_ID_REQUIRED) //Long에는 @NotEmpty 가 아니라 @NotNull
    private Long id;

    @Min(value = 1, message = ErrorMessage.PRODUCT_ORDER_RANGE)
    @Max(value = 255, message = ErrorMessage.PRODUCT_ORDER_RANGE)
    // 총 재고는 255개를 넘을 수 있다. 하지만, 구매 상한을 255개로 제한했다.
    private int quantity;
}
