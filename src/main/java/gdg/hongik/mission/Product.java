package gdg.hongik.mission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String productId;
    private String productName;
    private int productPrice;
    private int remainQuantity;
}
