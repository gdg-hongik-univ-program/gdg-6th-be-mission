package gdg.hongik.mission.Service;

import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.Repository.ProductRepository;
import gdg.hongik.mission.common.exception.BadRequestException;
import gdg.hongik.mission.common.exception.NotFoundException;
import gdg.hongik.mission.common.message.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List; //사용되지 않음

@Service
@RequiredArgsConstructor //final 필드를 매개변수로 받는 생성자를 lombok이 자동으로 만들어준다
public class ProductAdminService {
    private final ProductRepository productRepository; //생성자 주입, @RequiredArgsConstructor 해도 필수 선언

    @Transactional
    public Product createProduct(Product product) {
        Product existingProduct = productRepository.findByName(product.getName());

        if (existingProduct != null) {
            throw new BadRequestException(ErrorMessage.PRODUCT_ALREADY_EXISTS);
        }

        productRepository.save(product);

        return product;
    }


    // 관리자: 재고 추가
    @Transactional
    public Product addStock(Long id, int quantity) {
        Product product = productRepository.findById(id);

        if (product == null) {
            throw new NotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);
        }

        if (quantity <= 0) {
            throw new BadRequestException("추가할 재고 수량은 1개 이상이어야 합니다.");
        }

        product.setQuantity(product.getQuantity() + quantity);

        return product;
    }

    // 관리자: 상품 삭제
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id);

        if (product == null) {
            throw new NotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);
        }

        productRepository.deleteById(id);
    }
}
