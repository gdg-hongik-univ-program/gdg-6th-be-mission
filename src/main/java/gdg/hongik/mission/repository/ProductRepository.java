package gdg.hongik.mission.repository;
import gdg.hongik.mission.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByProductName(String productName);

    void deleteByProductName(String productName);

    // 상품 등록 시 '중복 검사'를 효율적으로 하기 위한 메서드 (리팩토링 과정 시 추가)
    boolean existsByProductName(String productName);
}
