package gdg.hongik.mission.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreateRequest(
        @NotBlank(message = "상품 이름은 공백일 수 없습니다.")
        String name,

        @NotNull(message = "재고 수량은 필수입니다.")
        Long stockQuantity,

        @NotNull(message = "가격은 필수입니다.")
        Long price) { }
