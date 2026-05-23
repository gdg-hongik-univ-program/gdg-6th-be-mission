package gdg.hongik.mission.dto.request;

import jakarta.validation.constraints.Positive;

public record StockUpdateRequest (

        @Positive
        int quantity
) {
}
