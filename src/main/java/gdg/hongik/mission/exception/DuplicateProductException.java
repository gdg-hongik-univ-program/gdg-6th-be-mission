package gdg.hongik.mission.exception;

// 상품명이 중복되었을 때(400) 던지는 구체적인 예외 클래스
public class DuplicateProductException extends BusinessException {
    public DuplicateProductException() {
        super(400, "상품이 이미 존재합니다.");
    }
}
