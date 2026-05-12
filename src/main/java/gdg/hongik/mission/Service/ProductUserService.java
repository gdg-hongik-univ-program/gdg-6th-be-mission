package gdg.hongik.mission.Service;

import gdg.hongik.mission.DTO.ProductBuyRequest;
import gdg.hongik.mission.DTO.ProductBuyResponse;
import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductUserService {

    private final ProductRepository productRepository;

    // 이름으로 상품 조회하기
    public Product findProductByName(String name) {

        //리포지토리에서 찾아오기
        Product product = productRepository.findByName(name);

        // 존재하지 않는 상품이면 RuntimeException 발생
        if(product == null) {
            throw new RuntimeException("존재하지 않는 상품입니다.");
        }

        // 존재하는 상품이면 정보 반환
        return product;
    }

    // 상품 재고 수정
    public ProductBuyResponse editProduct(ProductBuyRequest productBuyRequest) {

        // 응답 정보를 담기 위해 ProductBuyResponse DTO 생성
        ProductBuyResponse productBuyResponse = new ProductBuyResponse();

        // List 하나씩 돌며 수정 로직 호출하기
        for ( ProductBuyRequest.OrderRequest orderRequest : productBuyRequest.getOrderProducts() ) {

            //
            long id = orderRequest.getId();
            int quantity = orderRequest.getQuantity();

            Product product = productRepository.findById(id).get();

            // 응답 생성 로직 실행
            productBuyResponse.new OrderedProduct(product, quantity);

            // 재고 감소시키는 로직 수행
            product.decreaseStock(quantity);
            productRepository.save(product);
        }
        return productBuyResponse;
    }
}
