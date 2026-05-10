package gdg.hongik.mission.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;  // 상품Id (@GeneratedValue를 사용하기 위해
    private String productName;   // 상품 이름
    private int productPrice;    // 상품 하나당 가격
    private int remainQuantity;   // 현재 상품의 개수

}
