package gdg.hongik.mission.service;

import gdg.hongik.mission.dto.ProductAddResponse;
import gdg.hongik.mission.dto.ProductCreateRequest;
import gdg.hongik.mission.dto.ProductDeleteRequest;
import gdg.hongik.mission.dto.ProductDeleteResponse;
import gdg.hongik.mission.entity.Product;
import gdg.hongik.mission.exception.DuplicateProductNameException;
import gdg.hongik.mission.exception.ProductNotFoundException;
import gdg.hongik.mission.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductAdminService {
    private final ProductRepository productRepository;

    @Transactional
    public Product createProduct(ProductCreateRequest request){
        productRepository.findByName(request.name())
                .ifPresent(p -> {
                    throw new DuplicateProductNameException("동일한 이름의 상품이 존재합니다.");
                });

        //product 클래스는 기본생성자만 있으므로 setter로 product 생성
        //product 클래스에 id 제외한 나머지 필드만을 갖는 생성자 추가
        Product product = new Product(
                request.name(),
                request.stockQuantity(),
                request.price()
        );

        return productRepository.save(product);
    }

    @Transactional
    public ProductAddResponse addStock(Long id, Long addQuantity){

        Product targetProduct = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("해당 ID의 상품을 찾을 수 없습니다."));

        //Product 엔티티에 addStock 메서드 추가
        targetProduct.addStock(addQuantity);
        return new ProductAddResponse(targetProduct.getName(), targetProduct.getStockQuantity());

    }

    @Transactional
    public ProductDeleteResponse deleteProducts(ProductDeleteRequest request){
        // Spring Data Jpa가 제공하는 deleteAllByIdInBatch 사용하면 여러 Id를 한줄로 삭제가능
        productRepository.deleteAllByIdInBatch(request.productIds());
        // 삭제후 남은 모든 상품 조회
        List<Product> allProducts  = productRepository.findAll();
        // 삭제후 남은 모든 상품을 넣을 리스트
        List<ProductDeleteResponse.RemainingProduct> remainingList =new ArrayList<>();

        // 모든 상품 돌면서 리스트에 넣기
        for(Product p : allProducts){
            ProductDeleteResponse.RemainingProduct remainingProduct =
                    new ProductDeleteResponse.RemainingProduct(p.getName(), p.getStockQuantity());
            remainingList.add(remainingProduct);
        }

        return new ProductDeleteResponse(remainingList);


    }
}
