package gdg.hongik.mission.exception;

// 모든 비즈니스 커스텀 예외의 부모가 되는 클래스
public class BusinessException extends RuntimeException {
    private final int statusCode;

    public BusinessException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
