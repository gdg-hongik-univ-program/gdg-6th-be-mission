package gdg.hongik.mission.Service;

import gdg.hongik.mission.DTO.*;
import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.Repository.ProductRepository;
import gdg.hongik.mission.common.Exception.BadRequestException;
import gdg.hongik.mission.common.Exception.NotFoundException;
import gdg.hongik.mission.common.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAdminService {

    private final ProductRepository productRepository;

    // 새 상품 DB에 등록
    @Transactional
    public ProductDTO addProduct(ProductSaveRequest productSaveRequest) {

        // 중복 체크
        if(productRepository.findByName(productSaveRequest.name()).isPresent()){
            throw new BadRequestException(Message.PRODUCT_ALREADY_EXIST);
        }

        // 중복이 아니면 새 엔티티 만들기
        Product product = Product.builder()
                                .name(productSaveRequest.name())
                                .price(productSaveRequest.price())
                                .stock(productSaveRequest.stock())
                                .build();

        //DB에 저장하기
        productRepository.save(product);
        return ProductDTO.from(product);
    }


    // 상품 재고 추가
    @Transactional
    public StockAddResponse addStock(Long id, StockAddRequest stockAddRequest) {

        // DB에서 엔티티 찾아오고 유효성 검증
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(Message.PRODUCT_NOT_EXIST));

        product.addStock(stockAddRequest.additionalQuantity());

        return StockAddResponse.from(product);
    }

    @Transactional
    public ProductDeleteResponse deleteProduct(ProductDeleteRequest productDeleteRequest) {

        // 존재하는 상품인지 확인하며 하나씩 삭제한다.
        for (Long id : productDeleteRequest.productIds()) {

            // 존재하는 상품인지 확인
            Product product = productRepository.findById(id)
                    .orElseThrow(() ->new NotFoundException(Message.PRODUCT_NOT_EXIST));

            // 존재하면 삭제
            productRepository.delete(product);
        }

        // 삭제 후 남은 객체들 리스트에 담기
        List<Product> remains = productRepository.findAll();

        List<ProductDTO> remainProducts
                = remains.stream()
                .map(ProductDTO::from)
                .toList();

        return new ProductDeleteResponse(remainProducts);
    }
}
