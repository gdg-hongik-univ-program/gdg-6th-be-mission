package gdg.hongik.mission.Dto;

import gdg.hongik.mission.Message.ErrorMessage;
import jakarta.validation.constraints.*;

// 상품 구매 요청
public record PurchaseRequest(
        @NotBlank(message = ErrorMessage.NAME_NOT_BLANK)
        String name,

        @Min(value = 1, message = ErrorMessage.QUANTITY_MIN_ONE)
        int quantity
)
{
}