package gdg.hongik.mission.Service;

import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductAdminService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductAdminService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 새 상품 DB에 등록
    public void addProduct(Map<String, Object> request){

        // Map에서 데이터 추출 (타입 변환 필요)
        String name = (String) request.get("name");

        // JSON 숫자는 기본적으로 Integer 혹은 Double로 넘어오므로 안전하게 변환
        int price = Integer.valueOf(request.get("price").toString());
        int stock = Integer.valueOf(request.get("stock").toString());

        // 중복 체크
        if ( productRepository.findByName(name) != null) {
            throw new RuntimeException("이미 존재하는 상품 이름입니다.");
        }

        //DB에 저장하기
        productRepository.save(new Product(name, stock, price));
    }


    // 상품 재고 수정
    public void addStock(Long id, Map<String, Object> request) {

        int newStock = Integer.valueOf(request.get("additionalQuantity").toString());

        if(newStock <= 0) {
            throw new RuntimeException("추가할 재고는 양수여야합니다.");
        }

        Optional<Product> product = productRepository.findById(id);
        product.get().addStock(newStock);
        productRepository.save(product.get());
    }

    public List<Product> deleteProduct(Map<String, List<Long>> request) {

        // 명세서의 키값인 "productIds"로 리스트를 꺼낸다
        List<Long> ids = request.get("productIds");

        // 존재하는 상품인지 확인하며 하나씩 삭제한다.
        for (Long id : ids) {

            // 존재하는 상품인지 확인
            if(productRepository.findById(id).isEmpty()) {
                continue;
            }
            // 존재하면 삭제
            productRepository.deleteById(id);
        }

        // 삭제 후 남은 객체들 반환
        return productRepository.findAll();
    }
}
