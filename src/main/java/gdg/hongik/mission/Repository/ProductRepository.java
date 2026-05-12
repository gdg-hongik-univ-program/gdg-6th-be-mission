package gdg.hongik.mission.Repository;

import gdg.hongik.mission.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // 이름을 통해 상품을 찾아오는 메서드
    Product findByName(String name);

}
