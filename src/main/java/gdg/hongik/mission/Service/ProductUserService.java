package gdg.hongik.mission.Service;

import gdg.hongik.mission.DTO.ProductResponse;
import gdg.hongik.mission.DTO.PurchaseProductRequest;
import gdg.hongik.mission.DTO.PurchaseProductResponse;
import gdg.hongik.mission.Entity.Product;
import gdg.hongik.mission.Repository.ProductRepository;
import gdg.hongik.mission.common.exception.BadRequestException;
import gdg.hongik.mission.common.exception.NotFoundException;
import gdg.hongik.mission.common.message.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductUserService {
    private final ProductRepository productRepository;



    // 소비자: 상품명으로 상품 조회
    @Transactional(readOnly = true)
    public Product getProductByName(String name) {
        Product product = productRepository.findByName(name);

        if (product == null) {
            throw new NotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);
        }

        return product;
    }

    // 소비자: 상품 구매
    @Transactional
    public List<PurchaseProductResponse> purchaseProducts(List<PurchaseProductRequest> requestProducts) {
        List<PurchaseProductResponse> purchasedProducts = new ArrayList<>();

        for (PurchaseProductRequest requestProduct : requestProducts) {
            Product product = productRepository.findById(requestProduct.getId());

            if (product == null) {
                throw new NotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);
            }

            int requestQuantity = requestProduct.getQuantity();

            if (requestQuantity <= 0) {
                throw new BadRequestException("구매 수량은 1개 이상이어야 합니다.");
            }

            if (product.getQuantity() < requestQuantity) {
                throw new BadRequestException("재고가 부족합니다.");
            }

            product.setQuantity(product.getQuantity() - requestQuantity);

            PurchaseProductResponse purchasedProduct = new PurchaseProductResponse(
                    product.getId(),
                    product.getName(),
                    product.getPrice() * requestQuantity,
                    requestQuantity
            );

            purchasedProducts.add(purchasedProduct);

        }

        return purchasedProducts;
    }
}
