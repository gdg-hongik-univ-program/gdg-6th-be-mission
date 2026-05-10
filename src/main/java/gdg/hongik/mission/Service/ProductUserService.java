package gdg.hongik.mission.Service;

import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductUserService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductUserService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 이름으로 상품 찾아오기
    public Product findProductByName(String name) {

        //리포지토리에서 찾아오기
        Product product = productRepository.findByName(name);

        // 존재하지 않는 상품이면 RuntimeException 발생
        if(product == null) {
            throw new RuntimeException("존재하지 않는 상품입니다.");
        }

        // 존재하는 상품이면 정보 반환
        return product;
    }

    // 상품 재고 수정
    public void editProduct(Map<String, List<Map<String, Object>>> request) {

        // JSON의 "orderProducts" 키로 리스트를 꺼냅니다.
        List<Map<String, Object>> orderProducts = request.get("orderProducts");

        // Map 하나씩 돌며 수정 로직 호출하기
        for (Map<String, Object> item : orderProducts) {
            // Map에서 값을 꺼낼 때 형변환이 필요합니다.
            long id = Long.valueOf(item.get("id").toString());
            int quantity = (Integer) item.get("quantity");

            Product product = productRepository.findById(id).get();

            // 재고 감소시키는 로직 수행
            product.decreaseStock(quantity);
            productRepository.save(product);
        }
    }
}
