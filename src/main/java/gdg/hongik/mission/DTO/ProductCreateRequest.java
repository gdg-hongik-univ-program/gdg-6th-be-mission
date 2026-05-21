package gdg.hongik.mission.DTO;


import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.common.message.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
// 상품 등록 요청, 상품 생성
public class ProductCreateRequest {

    @NotNull
    private String name;

    @NotNull(message = ErrorMessage.PRODUCT_PRICE_RANGE)
    @Size(min = 100, max = 100000000)
    private double price;

    @NotNull(message = ErrorMessage.PRODUCT_QUANTITY_RANGE)
    @Size(min = 1, max = 255)
    private int quantity;

    //DTO에 담긴 내용으로 엔티티를 만들어서 전달합니다.
    public Product toEntity() {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        return product;
    }
}
