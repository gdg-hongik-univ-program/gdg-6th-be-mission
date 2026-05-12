package gdg.hongik.mission.DTO;

import gdg.hongik.mission.Entity.Product;

import java.util.List;

//총 구매 금액, 구매한 상품 목록 (상품명 / 구매 수량 / 해당 상품 소비 금액)
public record ProductBuyResponse (
    int totalPrice,
    List<OrderedProduct> orderedProductList
) {
    public record OrderedProduct (
        String name,
        int quantity,
        int subTotal
    ) {
        public static OrderedProduct from(Product product, int quantity, int subTotal) {
            return new  OrderedProduct(product.getName(), quantity, subTotal);
        }
    }
}
