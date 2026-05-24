package gdg.hongik.mission.Message;

public class ErrorMessage {
    public static final String PRODUCT_NOT_FOUND = "상품 없음";
    public static final String PRODUCT_ALREADY_EXISTS = "이미 존재하는 상품입니다.";
    public static final String OUT_OF_STOCK = "재고 부족";

    public static final String NAME_NOT_BLANK = "상품 이름은 필수 입력 항목입니다.";
    public static final String PRICE_MUST_BE_POSITIVE = "가격은 0원보다 커야 합니다.";
    public static final String STOCK_MUST_BE_POSITIVE_OR_ZERO = "재고는 0개 이상이어야 합니다.";
    public static final String QUANTITY_MUST_BE_POSITIVE = "추가할 수량은 1개 이상이어야 합니다.";
    public static final String NAME_SIZE = "상품 이름은 2자 이상, 20자 이하로 입력해 주세요.";
    public static final String QUANTITY_MIN_ONE = "구매 수량은 최소 1개여야 합니다. ";
}
