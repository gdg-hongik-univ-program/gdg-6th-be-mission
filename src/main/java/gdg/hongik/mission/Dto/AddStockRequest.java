package gdg.hongik.mission.Dto;

import gdg.hongik.mission.Message.ErrorMessage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

// 재고 추가
public record AddStockRequest(
        @NotBlank(message = ErrorMessage.NAME_NOT_BLANK)
        @Size(message = ErrorMessage.NAME_SIZE)
        String name,

        @Positive(message = ErrorMessage.QUANTITY_MUST_BE_POSITIVE)
        int quantity){
}
