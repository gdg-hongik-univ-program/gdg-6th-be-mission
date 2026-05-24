package gdg.hongik.mission.common;

public class Message {

    // 상품 관련
    public static final String PRODUCT_ALREADY_EXIST = "이미 존재하는 상품입니다";
    public static final String PRODUCT_NOT_EXIST = "존재하지 않는 상품입니다";


    // 상품 DTO 관련
    public static final String NAME_NOT_NULL = "이름은 필수입니다";
    public static final String NAME_NOT_BLANK = "이름이 비어있습니다";

    public static final String PRICE_NOT_NULL = "가격은 필수입니다";
    public static final String PRICE_NOT_NEGATIVE = "가격은 양수여야합니다";

    public static final String STOCK_BIGGER_THAN_1 = "재고는 1보다 커야합니다";
    public static final String STOCK_NOT_NULL = "재고는 필수입니다";

    public static final String QUANTITY_OUT_OF_RANGE = "추가 수량은 1보다 커야합니다";

    public static final String LIST_NOT_NULL = "리스트는 필수입니다.";
    public static final String LIST_NOT_EMPTY = "ID 리스트가 비어있습니다";

    public static final String ID_NOT_NULL = "ID값은 필수입니다.";
    public static final String ID_NOT_NEGATIVE = "ID은 양수여야합니다";


}
