package gdg.hongik.mission.DTO;

import gdg.hongik.mission.common.Message;
import jakarta.validation.constraints.*;

// Admin이 상품 등록 요청에 사용
public record ProductSaveRequest (

        @NotEmpty(message = Message.NAME_NOT_BLANK)
        String name,

        @NotNull(message = Message.PRICE_NOT_NULL)
        @Min(value = 1, message = Message.PRICE_NOT_NEGATIVE)
        int price,

        @NotNull(message = Message.STOCK_NOT_NULL)
        @Min(value = 1, message = Message.STOCK_BIGGER_THAN_1)
        int stock
) {}
