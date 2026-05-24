package gdg.hongik.mission.exception;

public class ProductNotFoundException
        extends RuntimeException {

    public ProductNotFoundException() {
        super("상품이 존재하지 않습니다.");
    }
}
