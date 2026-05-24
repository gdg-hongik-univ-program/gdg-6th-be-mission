package gdg.hongik.mission.exception;

// 상품이 없을 때(404) 던지는 구체적인 예외 클래스
public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException() {
        super(404, "해당 상품을 찾을 수 없습니다.");
    }
}
