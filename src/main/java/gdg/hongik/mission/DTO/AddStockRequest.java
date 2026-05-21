package gdg.hongik.mission.DTO;

import gdg.hongik.mission.common.message.ErrorMessage;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
// 재고 추가 요청
public class AddStockRequest {

    @Min(value = 1, message = ErrorMessage.PRODUCT_QUANTITY_RANGE)
    @Max(value = 255, message = ErrorMessage.PRODUCT_QUANTITY_RANGE)
    private int addQuantity;
}
