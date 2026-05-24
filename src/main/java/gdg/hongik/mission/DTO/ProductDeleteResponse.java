package gdg.hongik.mission.DTO;

import java.util.List;

// 상품 삭제 응답용 DTO
public record ProductDeleteResponse (
        // 삭제를 완료한 뒤 남은 상품들을 담을 리스트
        List<ProductDTO> remainProductList
) {
}
