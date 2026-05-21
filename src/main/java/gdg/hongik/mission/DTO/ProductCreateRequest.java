package gdg.hongik.mission.DTO;


import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.common.message.ErrorMessage;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
// 상품 등록 요청, 상품 생성
public class ProductCreateRequest {

    @NotBlank(message = ErrorMessage.PRODUCT_NAME_REQUIRED)
    private String name;

    @Min(value = 100, message = ErrorMessage.PRODUCT_PRICE_RANGE)
    @Max(value = 100000000, message = ErrorMessage.PRODUCT_PRICE_RANGE)
    private double price;

    @Min(value = 1, message = ErrorMessage.PRODUCT_QUANTITY_RANGE)
    @Max(value = 255, message = ErrorMessage.PRODUCT_QUANTITY_RANGE)
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
