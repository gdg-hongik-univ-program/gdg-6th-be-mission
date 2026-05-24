package gdg.hongik.mission.DTO;

import gdg.hongik.mission.common.Message;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record ProductDeleteRequest (
        @NotEmpty(message = Message.LIST_NOT_EMPTY)
        List<   @NotNull(message = Message.ID_NOT_NULL)
                @Positive(message = Message.ID_NOT_NEGATIVE)
                Long> productIds
){}
