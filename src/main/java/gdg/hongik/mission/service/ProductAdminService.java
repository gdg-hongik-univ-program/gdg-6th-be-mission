package gdg.hongik.mission.service;

import gdg.hongik.mission.entity.Product;
import gdg.hongik.mission.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAdminService {

    private final ProductRepository productRepository;

    // 상품 등록
    public Product createProduct(Product product) {

        Product existingProduct =
                productRepository.findByName(product.getName());

        if (existingProduct != null) {
            throw new RuntimeException("이미 존재하는 상품명");
        }

        return productRepository.save(product);
    }

    // 재고 추가
    public Product addStock(Long id, int quantity) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("상품 없음"));

        product.setStock(product.getStock() + quantity);

        return productRepository.save(product);
    }

    // 여러 상품 삭제
    public List<Product> deleteProducts(List<Long> ids) {

        List<Product> products = productRepository.findAllById(ids);

        productRepository.deleteAll(products);

        return productRepository.findAll();
    }
}
