package gdg.hongik.mission.Service;

import gdg.hongik.mission.DTO.ProductBuyRequest;
import gdg.hongik.mission.DTO.ProductBuyResponse;
import gdg.hongik.mission.DTO.ProductDTO;
import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.Repository.ProductRepository;
import gdg.hongik.mission.common.Exception.NotFoundException;
import gdg.hongik.mission.common.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductUserService {

    private final ProductRepository productRepository;

    // 이름으로 상품 조회하기
    @Transactional(readOnly = true)
    public ProductDTO findProductByName(String name) {

        // 리포지토리에서 조회하기
        Product product = productRepository.findByName(name)
                .orElseThrow(()-> new NotFoundException(Message.PRODUCT_NOT_EXIST));

        // 존재하는 상품이면 정보 반환
        return  ProductDTO.from(product);
    }

    // 상품 구매 (상품 재고 감소시키기)
    @Transactional
    public ProductBuyResponse buyProducts(ProductBuyRequest productBuyRequest) {

        // 응답 정보를 담기 위해 ProductBuyResponse DTO 생성
        List<ProductBuyResponse.OrderedProduct> orderedProductList = new ArrayList<>();

        int totalPrice = 0;

        // List 하나씩 돌며 수정 로직 호출하기
        for ( ProductBuyRequest.OrderRequest orderRequest : productBuyRequest.orderProducts() ) {

            // 수량 값 꺼내기
            int quantity = orderRequest.quantity();

            // 존재하는 상품인지 확인
            Product product = productRepository.findById(orderRequest.id())
                    .orElseThrow(()-> new NotFoundException(Message.PRODUCT_NOT_EXIST));

            // 해당 상품 총 구매액과 전체 주문액 계산
            int subTotal = product.getPrice() * quantity;

            totalPrice += subTotal;

            // 응답 생성 로직 실행
            orderedProductList.add(
                    ProductBuyResponse.OrderedProduct.from(product, quantity, subTotal));

            // 재고 감소시키는 로직 수행
            product.decreaseStock(quantity);
        }

        return new ProductBuyResponse(totalPrice, orderedProductList);
    }
}
