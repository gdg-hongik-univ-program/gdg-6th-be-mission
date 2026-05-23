package gdg.hongik.mission.entity;

import gdg.hongik.mission.exception.InsufficientStockException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    //상품 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //상품 이름
    private String name;

    private Long stockQuantity;

    private Long price;

    public Product(String name, Long stockQuantity, Long price){
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.price = price;
    }

    public void addStock(Long quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(Long quantity) {
        if (this.stockQuantity < quantity) {
            throw new InsufficientStockException("재고가 부족합니다.");
        }
        this.stockQuantity -= quantity;
    }
}
