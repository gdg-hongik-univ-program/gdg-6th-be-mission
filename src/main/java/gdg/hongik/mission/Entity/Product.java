package gdg.hongik.mission.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int stock;
    private int price;

    // 재고 수 증가 메서드 - 어드민이 재고 추가 시 사용
    public void addStock(int newStock) {

        if ( newStock < 0 ) {
            throw new RuntimeException("추가되는 재고는 양수여야합니다.") ;
        }

        stock += newStock;
    }

    // 재고 수 감소 메서드 - 유저가 상품 주문 시 사용
    public void decreaseStock(int stock) {
        if ( this.stock < stock) {
            throw new RuntimeException("재고가 부족합니다.");
        }
        this.stock -= stock;
    }
}
