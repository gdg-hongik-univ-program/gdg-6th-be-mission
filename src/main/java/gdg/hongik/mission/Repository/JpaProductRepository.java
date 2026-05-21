package gdg.hongik.mission.Repository;

import gdg.hongik.mission.Entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

// ProductRepository 인터페이스를 JPA 방식으로 구현
@Repository
public class JpaProductRepository implements ProductRepository {

    @PersistenceContext //JPA의 EntityManager를 스프링이 자동으로 넣어주도록 하는 어노테이션
    private EntityManager em;


    // 상품 id로 상품 1개 조회
    @Override
    public Product findById(Long id) {
        return em.find(Product.class, id);
    }


    // 상품명으로 상품 1개 조회
    @Override
    public Product findByName(String name) {
        List<Product> result = em.createQuery(
                        "SELECT p FROM Product p WHERE p.name = :name", Product.class)
                .setParameter("name", name)
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    // 상품 DB에 저장
    @Override
    public void save(Product product) {
        em.persist(product);
    }

    //상품 id로 상품 1개 삭제
    @Override
    public void deleteById(Long id) {
        Product product = em.find(Product.class, id);

        if (product != null)
            em.remove(product);

    }

}
