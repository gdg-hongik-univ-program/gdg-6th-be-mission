package gdg.hongik.mission.dto;

// 상품 등록시 사용하는 dto
public record ProductCreateRequest (String productName, int productPrice, int remainQuantity){
}