package gdg.hongik.mission.DTO;

import gdg.hongik.mission.common.message.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
// 관리자 상품 삭제 요청
public class ProductDeleteRequest {

    @NotNull(message = ErrorMessage.PRODUCT_ID_REQUIRED)
    private Long id;
}
