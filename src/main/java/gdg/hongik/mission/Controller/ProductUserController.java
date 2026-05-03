package gdg.hongik.mission.Controller;

import gdg.hongik.mission.ProductStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductUserController {


    //상품 조회
    @GetMapping("/products")
    public ResponseEntity<?> getProducts(@RequestParam(required = false) String name) {

        Product product = ProductStore.findByName(name);

        // 존재하지 않는 상품이면 RuntimeException 발생
        if(product == null) {
            throw new RuntimeException("존재하지 않는 상품입니다.");
        }

        // 존재하는 상품이면 정보 반환
        return ResponseEntity.ok(product);
    }

    //상품 구매
    @PostMapping("/orders")
    public ResponseEntity<?> buyProducts(@RequestBody Map<String, List<Map<String, Object>>> request) {

        // JSON의 "orderProducts" 키로 리스트를 꺼냅니다.
        List<Map<String, Object>> orderProducts = request.get("orderProducts");

        for (Map<String, Object> item : orderProducts) {
            // Map에서 값을 꺼낼 때 형변환이 필요합니다.
            long id = Long.valueOf(item.get("id").toString());
            int quantity = (Integer) item.get("quantity");

            // 재고 수정 로직 수행
            ProductStore.editStock(id, quantity);
        }
        return ResponseEntity.status(201).build();
    }
}
