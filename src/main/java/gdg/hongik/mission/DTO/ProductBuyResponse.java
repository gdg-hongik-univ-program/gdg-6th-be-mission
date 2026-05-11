package gdg.hongik.mission.DTO;

import gdg.hongik.mission.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//총 구매 금액, 구매한 상품 목록 (상품명 / 구매 수량 / 해당 상품 소비 금액)
@NoArgsConstructor
public class ProductBuyResponse {

    public int totalPrice=0;

    public List<OrderedProduct> orderedProductList = new ArrayList<>();

    @Getter
    public class OrderedProduct {

        String name;
        int quantity;
        int subTotal;

        public OrderedProduct(Product product, int quantity) {

            // 이름은 엔티티에서 꺼내 초기화
            this.name = product.getName();

            // 주문 수량은 서비스에서 파라미터로 받아 초기화
            this.quantity = quantity;

            // 해당 상품 총 구매액과 전체 주문액 계산
            subTotal = product.getPrice() * quantity;
            totalPrice += subTotal;

            // 리스트에 추가해주기
            orderedProductList.add(this);
        }
    }
}
