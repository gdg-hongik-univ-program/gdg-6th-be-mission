package gdg.hongik.mission.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int stock;
    private int price;

    // 생성자
    public Product(String name, int stock, int price) {
        this.price = price;
        this.name = name;
        this.stock = stock;
    }

    public void addStock(int newStock) {

        if ( newStock < 0 ) {
            throw new RuntimeException("추가되는 재고는 양수여야합니다.") ;
        }

        stock += newStock;
    }

    public void decreaseStock(int stock) {
        if ( this.stock < stock) {
            throw new RuntimeException("재고가 부족합니다.");
        }
        this.stock -= stock;
    }
}
