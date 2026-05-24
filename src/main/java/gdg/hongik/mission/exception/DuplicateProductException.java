package gdg.hongik.mission.exception;

public class DuplicateProductException
        extends RuntimeException {

    public DuplicateProductException() {
        super("이미 존재하는 상품명입니다.");
    }
}