package gdg.hongik.mission.Controller;

import gdg.hongik.mission.DTO.ProductResponse;
import gdg.hongik.mission.DTO.PurchaseProductRequest;
import gdg.hongik.mission.DTO.PurchaseProductResponse;
import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.Service.ProductUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



// swagger 문서화 작업 -> http://localhost:8080/swagger-ui/index.html

//192.168.134.160
@CrossOrigin(origins = "*")
@RestController
// 이 클래스가 REST API 요청을 처리하는 Controller이다.
// 각 메서드의 반환값은 HTML 화면이 아니라 JSON 응답 데이터로 반환
@RequestMapping("/user/products") // 이 Controller의 기본 URL 경로를 /user/products로 설정
@RequiredArgsConstructor
public class ProductUserController {

    private final ProductUserService productUserService;





    // 소비자: 상품명으로 상품 조회
    //GET 요청으로 쿼리 파라미터로 상품명을 받는다.
    // 요청 예시: GET /user/products?name=얼박사 아래부터 POSTMAN 실습
    // 응답 예시:
    //{
    //    "id": 1,
    //    "name": "얼박사",
    //    "price": 3500.0,
    //    "quantity": 15
    //}
    @GetMapping
    public ResponseEntity<ProductResponse> getProduct(@RequestParam String name) {
        Product product = productUserService.getProductByName(name);

        return ResponseEntity.ok(new ProductResponse(product));
    }

    // 소비자: 상품 구매
    // 요청 예시:
    //[
    //    {
    //        "id": 1,
    //        "quantity": 2
    //    },
    //    {
    //        "id":2,
    //        "quantity": 2
    //    }
    //]
    // 응답 예시:
    //[
    //    {
    //        "id": 1,
    //        "name": "얼박사",
    //        "totalPrice": 7000.0,
    //        "quantity": 2
    //    },
    //    {
    //        "id": 2,
    //        "name": "얼포박",
    //        "totalPrice": 9000.0,
    //        "quantity": 2
    //    }
    //]
    @PostMapping("/order") // 3주차에서 경로 수정
    public ResponseEntity<List<PurchaseProductResponse>> purchaseProduct(
            @RequestBody List<PurchaseProductRequest> requestProducts)
    {
        List<PurchaseProductResponse> purchasedProducts = productUserService.purchaseProducts(requestProducts);

        return ResponseEntity.ok(purchasedProducts);
    }

}