package gdg.hongik.mission.common.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message); //부모클래스의 생성자
    }
}
