package gdg.hongik.mission.exception;

// 상품 재고 부족 시(400) 던지는 구체적인 비즈니스 예외 클래스
public class OutOfStockException extends BusinessException {

    public OutOfStockException() {
        // HTTP 상태 코드 400과 클라이언트에게 보여줄 에러 메시지를 부모 클래스로 전달
        super(400, "상품의 재고가 부족하여 주문할 수 없습니다.");
    }
}