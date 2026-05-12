package gdg.hongik.mission.Service;

import gdg.hongik.mission.DTO.*;
import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAdminService {

    private final ProductRepository productRepository;

    // 새 상품 DB에 등록
    public void addProduct(ProductSaveRequest productSaveRequest) {

        // 중복 체크
        if ( productRepository.findByName(productSaveRequest.getName()) != null) {
            throw new RuntimeException("이미 존재하는 상품 이름입니다.");
        }

        Product product = Product.builder()
                                .name(productSaveRequest.getName())
                                .price(productSaveRequest.getPrice())
                                .stock(productSaveRequest.getStock())
                                .build();
        
        //DB에 저장하기
        productRepository.save(product);
    }


    // 상품 재고 수정
    public StockAddResponse addStock(Long id, StockAddRequest stockAddRequest) {

        int newStock = stockAddRequest.getAdditionalQuantity();

        if(newStock <= 0) {
            throw new RuntimeException("추가할 재고는 양수여야합니다.");
        }

        Product product = productRepository.findById(id).get();
        product.addStock(newStock);
        productRepository.save(product);

        return new StockAddResponse(product.getName(), product.getStock());
    }

    public ProductDeleteResponse deleteProduct(ProductDeleteRequest productDeleteRequest) {

        // 존재하는 상품인지 확인하며 하나씩 삭제한다.
        for (Long id : productDeleteRequest.getProductIds()) {

            // 존재하는 상품인지 확인
            if(productRepository.findById(id).isEmpty()) {
                continue;
            }
            // 존재하면 삭제
            productRepository.deleteById(id);
        }

        // 삭제 후 남은 객체들 리스트에 담기
        List<Product> remains = productRepository.findAll();

        ProductDeleteResponse deleteResponse = new ProductDeleteResponse();

        for(Product product : remains) {
            deleteResponse.new RemainProduct(
                    product.getName(),
                    product.getStock()
            );
        }
        return deleteResponse;
    }
}
