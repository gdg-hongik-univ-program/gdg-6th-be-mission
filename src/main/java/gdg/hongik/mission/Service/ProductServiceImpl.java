package gdg.hongik.mission.Service;

import gdg.hongik.mission.Dto.*;
import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.Exception.BadRequestException;
import gdg.hongik.mission.Exception.NotFoundException;
import gdg.hongik.mission.Message.ErrorMessage;
import gdg.hongik.mission.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    //상품 조회
    @Override
    @Transactional(readOnly = true)
    public GetProductResponse getProduct(String name){

        Product found = productRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.PRODUCT_NOT_FOUND));

        return new GetProductResponse(found);
    }

    // 상품 구매
    @Override
    @Transactional
    public String purchaseProducts(List<PurchaseRequest> requests){

        int totalPrice = 0;
        String result = "";

        for (PurchaseRequest request : requests) {
            // 상품 찾기
            Product found = productRepository.findByName(request.name())
                    .orElseThrow(() -> new NotFoundException(ErrorMessage.PRODUCT_NOT_FOUND));

            // 재고 확인
            if (found.getStock() < request.quantity()) {
                throw new BadRequestException(found.getName() + ErrorMessage.OUT_OF_STOCK);
            }

            // 재고 감소
            found.setStock(found.getStock() - request.quantity());

            int price = found.getPrice() * request.quantity();
            totalPrice += price;

            result += found.getName() + " "
                    + request.quantity() + "개 구매 ("
                    + price + "원)\n";
        }

        result += "총 금액: " + totalPrice;
        return result;
    }

    // 상품 등록
    @Override
    @Transactional
    public void createProduct(CreateProductRequest request) {

        productRepository.findByName(request.name())
                .ifPresent(p -> {
                    throw new BadRequestException(ErrorMessage.PRODUCT_ALREADY_EXISTS);
                });

        // DTO 상자는 저장이 안되서 엔티티에 이 값을 넣어줘야 한다.
        Product product = new Product();
        product.setName(request.name());
        product.setPrice(request.price());
        product.setStock(request.stock());

        productRepository.save(product);
    }

    // 재고 추가
    @Override
    @Transactional
    public void addStock(AddStockRequest request) {
        Product found = productRepository.findByName(request.name())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.PRODUCT_NOT_FOUND));

        found.setStock(found.getStock() + request.quantity());
    }

    // 상품 삭제
    @Override
    @Transactional
    public void deleteProducts(DeleteProductsRequest request) {
        // 복잡한 for문 대신 JPA에서 제공하는 삭제 기능을 사용
        Product found = productRepository.findByName(request.name())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.PRODUCT_NOT_FOUND));

        productRepository.delete(found);
    }
}
