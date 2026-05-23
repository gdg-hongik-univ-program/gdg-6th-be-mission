package gdg.hongik.mission.repository;

import gdg.hongik.mission.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // findByName 쿼리는 관례에 맞춰 메서드 이름만 선언하면 스프링이 알아서 구현해줌.
    Optional<Product> findByName(String name);
}