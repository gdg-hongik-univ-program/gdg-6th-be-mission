package gdg.hongik.mission.dto;

import java.util.List;

public record OrderRequest (List<OrderProductRequest> orderProducts){
}
