package gdg.hongik.mission.DTO;

import gdg.hongik.mission.Entity.Product;

import java.util.List;

// 상품 삭제 응답용 DTO
public record ProductDeleteResponse (
        // 삭제를 완료한 뒤 남은 상품들을 담을 리스트
        List<RemainProduct> remainProductList
) {
    // 남아있는 상품별로 이름, 재고를 RemainProduct에 담는다
    public record RemainProduct (
            String name,int stock
    ) {
        // 엔티티 정보를 통해 응답 생성할 때 쓸 메서드
        public static RemainProduct from(Product product) {
            return new RemainProduct(product.getName(), product.getStock());
        }
    }
}
