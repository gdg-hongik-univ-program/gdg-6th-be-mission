package gdg.hongik.mission.common.message;

public class ErrorMessage {
    //Product 관련 에러 메시지
    public static final String PRODUCT_NOT_FOUND = "해당 상품을 찾을 수 없습니다.";
    public static final String PRODUCT_ALREADY_EXISTS = "이미 존재하는 상품입니다.";
    public static final String PRODUCT_STOCK_NOT_ENOUGH = "재고가 부족합니다.";
    public static final String PRODUCT_INFO_REQUIRED = "상품 정보 입력은 필수입니다.";
    public static final String PRODUCT_ORDER_RANGE = "구매하실 상품 수량은 1개 이상 255개 이하입니다.";

    //DTO 관련
    public static final String PRODUCT_ID_REQUIRED = "상품 ID 입력은 필수입니다.";
    public static final String PRODUCT_NAME_REQUIRED = "상품 이름 입력은 필수입니다.";
    public static final String PRODUCT_PRICE_RANGE = "상품 가격은 100원 이상 1억원 이하 입니다.";
    public static final String PRODUCT_QUANTITY_RANGE = "등록하실 상품 수량은 1개 이상 255개 이하입니다.";
    public static final String ADD_PRODUCT_QUANTITY_RANGE = "추가하실 상품 수량은 1개 이상 255개 이하입니다.";

    public static final String SERVER_ERROR = "서버 내부 에러가 발생했습니다.";

}
