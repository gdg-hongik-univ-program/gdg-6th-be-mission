package gdg.hongik.mission.DTO;

// Admin이 상품 등록 요청에 사용
public record ProductSaveRequest (
        String name, int stock, int price
) {}
