package gdg.hongik.mission.Repository;


import gdg.hongik.mission.Entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.List;

// 원래는 DB에 접근하는 저장소 역할의 클래스에 붙이는 어노테이션
// "상품 데이터를 저장하는 역할"을 하므로 Repository로 표시
//ProductStore -> ProductRepository
@Repository
public class ProductRepository {

    @PersistenceContext //JPA의 EntityManager를 스프링이 자동으로 넣어주도록 하는 어노테이션
    private EntityManager em;


    // 상품 id로 상품 1개 조회
    public Product findById(Long id) { return em.find(Product.class, id); }


    // 상품명으로 상품 1개 조회
    public Product findByName(String name) {
        List<Product> result = em.createQuery(
                        "SELECT p FROM Product p WHERE p.name = :name", Product.class)
                .setParameter("name", name)
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }
    
    // 상품 DB에 저장
    public void save(Product product) {
        em.persist(product);
    }

    //상품 id로 상품 1개 삭제
    public void deleteById(Long id) {
        Product product = em.find(Product.class, id);
        if (product != null)
            em.remove(product);

    }

}