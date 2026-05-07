package gdg.hongik.mission.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String productId;  // 상품Id
    private String productName;   // 상품 이름
    private int productPrice;    // 상품 하나당 가격
    private int remainQuantity;   // 현재 상품의 개수

}
