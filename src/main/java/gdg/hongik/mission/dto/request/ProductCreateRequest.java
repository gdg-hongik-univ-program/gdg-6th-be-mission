package gdg.hongik.mission.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductCreateRequest(

        @NotBlank
        String name,

        @Positive
        int price,

        @PositiveOrZero
        int stock
) {
}