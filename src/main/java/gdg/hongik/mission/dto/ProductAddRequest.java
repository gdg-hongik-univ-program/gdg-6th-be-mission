package gdg.hongik.mission.dto;

import jakarta.validation.constraints.NotNull;

public record ProductAddRequest(
        @NotNull(message = "추가 수량은 필수입니다.")
        Long addQuantity) {
}
