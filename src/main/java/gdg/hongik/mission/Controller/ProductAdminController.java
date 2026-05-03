package gdg.hongik.mission.Controller;

import gdg.hongik.mission.ProductStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductAdminController {

    //상품 등록
    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestBody Map<String, Object> request) {

        // 1. Map에서 데이터 추출 (타입 변환 필요)
        String name = (String) request.get("name");

        // JSON 숫자는 기본적으로 Integer 혹은 Double로 넘어오므로 안전하게 변환
        int price = Integer.valueOf(request.get("price").toString());
        int stock = Integer.valueOf(request.get("stock").toString());

        // 2. 중복 체크
        if (ProductStore.findByName(name) != null) {
            throw new RuntimeException("이미 존재하는 상품 이름입니다.");
        }

        Product product = new Product(name, stock, price);

        ProductStore.createProduct(product);

        return ResponseEntity.ok().build();
    }

    //재고 추가
    @PatchMapping("/products/{id}/stock")
    public ResponseEntity<?> addStock(@PathVariable Long id, @RequestBody Map<String, Object> request) {

        int newStock = Integer.valueOf(request.get("additionalQuantity").toString());

        ProductStore.addStock(id, newStock);

        return ResponseEntity.ok().build();
    }

    //상품 삭제
    @DeleteMapping("/products")
    public ResponseEntity<?> deleteProduct(@RequestBody Map<String, List<Long>> request) {

        // 명세서의 키값인 "productIds"로 리스트를 꺼낸다
        List<Long> ids = request.get("productIds");

        ProductStore.products.removeIf(p -> ids.contains(p.getId()));

        /*for (Long id : ids) {
            ProductStore.deleteProduct(id);
        }*/

        // 명세서의 응답 형식: 삭제 후 남아있는 상품 목록 반환
        Map<String, Object> response = new HashMap<>();
        response.put("remainProducts", ProductStore.products);

        return ResponseEntity.ok(response);
    }
}
