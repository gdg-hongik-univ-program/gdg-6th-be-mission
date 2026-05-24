package gdg.hongik.mission.Dto;

import gdg.hongik.mission.Message.ErrorMessage;
import jakarta.validation.constraints.*;

// 상품 등록 요청
public record CreateProductRequest(
        @NotBlank(message = ErrorMessage.NAME_NOT_BLANK)
        @Size(min = 2, max = 20, message = ErrorMessage.NAME_SIZE)
        String name,

        @Positive(message = ErrorMessage.PRICE_MUST_BE_POSITIVE)
        int price,

        @PositiveOrZero(message = ErrorMessage.STOCK_MUST_BE_POSITIVE_OR_ZERO)
        int stock)
{
}
