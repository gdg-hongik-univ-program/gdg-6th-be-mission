package gdg.hongik.mission.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record PurchaseListRequest(
        @NotEmpty(message = "구매할 상품을 하나 이상 선택해주세요.")
        @Valid
        List<PurchaseRequest> requests
)
{
}
