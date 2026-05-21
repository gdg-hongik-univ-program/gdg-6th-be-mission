package gdg.hongik.mission.DTO;

import gdg.hongik.mission.common.message.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
// 재고 추가 요청
public class AddStockRequest {
    @NotNull(message = ErrorMessage.PRODUCT_QUANTITY_RANGE)
    @Size(min = 1, max = 255)
    private int addQuantity;
}
