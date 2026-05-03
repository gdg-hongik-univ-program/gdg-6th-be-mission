package gdg.hongik.mission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String productId;  // 상품Id
    private String productName;   // 상품 이름
    private int productPrice;    // 상품 하나당 가격
    private int remainQuantity;   // 현재 상품의 개수
}
