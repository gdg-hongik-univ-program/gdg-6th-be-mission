package gdg.hongik.mission.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductBuyRequest(

        @NotNull
        Long id,

        @Positive
        int quantity
) {
}
