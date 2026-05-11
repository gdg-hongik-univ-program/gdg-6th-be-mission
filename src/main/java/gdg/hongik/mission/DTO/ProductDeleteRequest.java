package gdg.hongik.mission.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductDeleteRequest {

    private List<Long> productIds;
}
