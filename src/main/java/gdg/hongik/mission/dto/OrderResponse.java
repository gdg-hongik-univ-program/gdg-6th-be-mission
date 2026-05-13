package gdg.hongik.mission.dto;

import java.util.List;

public record OrderResponse (Long orderId, int price, List<OrderProductResponse> products){}
