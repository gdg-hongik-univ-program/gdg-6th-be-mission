package gdg.hongik.mission.Controller;

import gdg.hongik.mission.DTO.AddStockRequest;
import gdg.hongik.mission.DTO.ProductCreateRequest;
import gdg.hongik.mission.DTO.ProductDeleteRequest;
import gdg.hongik.mission.DTO.ProductResponse;
import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.Service.ProductAdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//UserController에서 어노테이션 설명
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin/products")
@RequiredArgsConstructor
public class ProductAdminController {


    private final ProductAdminService productAdminService;

    // 관리자: 상품 등록
    // 요청 예시:
    // {
    //   "name": "얼박사",
    //   "price": 3500,
    //   "quantity": 10
    // }
    // 응답 예시:
    //{
    //    "id": 1,
    //    "name": "얼박사",
    //    "price": 3500.0,
    //    "quantity": 10
    //}
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductCreateRequest request) {
        Product savedProduct = productAdminService.createProduct(request.toEntity());


        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductResponse(savedProduct));
    }

    // 관리자: 재고 추가
    // 특정 상품 ID에 해당하는 상품의 재고를 추가한다.
    // URL의 {productId}로 상품을 찾고,
    // RequestBody의 숫자를 추가할 재고 수량으로 사용한다.
    //
    // 요청 예시:
    // PATCH /admin/products/1
    //{
    //  "addQuantity": 5
    //}
    // 응답 예시:
    //{
    //    "id": 1,
    //    "name": "얼박사",
    //    "price": 3500.0,
    //    "quantity": 15
    //}
    @PatchMapping("/{productId}")
    public ResponseEntity<ProductResponse> addStock(@PathVariable Long productId, @Valid @RequestBody AddStockRequest request) {
        Product product = productAdminService.addStock(productId, request.getAddQuantity());

        return ResponseEntity.ok(new ProductResponse(product));
    }

    // 관리자: 상품 삭제
    // 요청 예시:
    // [
    //   {
    //     "id": 1
    //   },
    //   {
    //     "id": 2
    //   }
    // ]
    // 응답 예시: 상품들이 삭제되었습니다.
    //여러 상품을 한번에 삭제하기 위해 삭제할 상품 정보를 List<Product> 형태로 받는다.
    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@Valid @RequestBody List<@Valid ProductDeleteRequest> requestProducts) {
        for (ProductDeleteRequest requestProduct : requestProducts) {
            productAdminService.deleteProduct(requestProduct.getId());
        }

        return ResponseEntity.ok("상품들이 삭제되었습니다.");
    }

}