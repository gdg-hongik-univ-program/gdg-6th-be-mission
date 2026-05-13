package gdg.hongik.mission.dto;

import java.util.List;

public record OrderResponse (String orderId, int price, List<OrderProductResponse> products){}
