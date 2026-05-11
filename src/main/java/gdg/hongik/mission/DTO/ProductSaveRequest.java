package gdg.hongik.mission.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Admin이 상품 등록 요청, 응답할 때 사용
@Getter
@NoArgsConstructor
public class ProductSaveRequest {

    private String name;
    private int stock;
    private int price;

    ProductSaveRequest(String name, int stock, int price) {
        this.name = name;
        this.stock = stock;
        this.price = price;
    }
}
