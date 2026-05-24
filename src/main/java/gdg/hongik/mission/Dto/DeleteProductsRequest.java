package gdg.hongik.mission.Dto;

import gdg.hongik.mission.Message.ErrorMessage;
import jakarta.validation.constraints.NotBlank;

// 상품 삭제 요청
public record DeleteProductsRequest(
        @NotBlank(message = ErrorMessage.NAME_NOT_BLANK)
        String name) {
}
