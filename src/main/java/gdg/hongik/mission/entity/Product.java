package gdg.hongik.mission.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int price;
    private int stock;

    public Product(
            String name,
            int price,
            int stock
    ) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void addStock(int quantity) {
        this.stock += quantity;
    }

    public void removeStock(int quantity) {
        this.stock -= quantity;
    }
}