package gdg.hongik.mission.DTO;

import gdg.hongik.mission.common.Message;
import jakarta.validation.constraints.Min;

public record StockAddRequest (

        @Min(value = 1, message = Message.QUANTITY_OUT_OF_RANGE)
        int additionalQuantity)
{
}
