package gdg.hongik.mission.Service;

import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.Repository.ProductRepository;
import gdg.hongik.mission.common.exception.BadRequestException;
import gdg.hongik.mission.common.exception.NotFoundException;
import gdg.hongik.mission.common.message.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor //final 필드를 매개변수로 받는 생성자를 lombok이 자동으로 만들어준다
public class ProductAdminService {
    private final ProductRepository productRepository; //생성자 주입, @RequiredArgsConstructor 해도 필수 선언
    
    // 관리자: 상품 등록
    @Transactional
    public Product createProduct(Product product) {

        if (product == null) {
            throw new BadRequestException(ErrorMessage.PRODUCT_INFO_REQUIRED);
        }

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

        if (quantity < 1 || quantity > 255) {
            throw new BadRequestException(ErrorMessage.PRODUCT_QUANTITY_RANGE);
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
