package gdg.hongik.mission.service;

import gdg.hongik.mission.dto.*;
import gdg.hongik.mission.entity.Product;
import gdg.hongik.mission.exception.DuplicateProductException;
import gdg.hongik.mission.exception.ProductNotFoundException;
import gdg.hongik.mission.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductAdminService {
        private final ProductRepository productRepository;

    public ProductAdminService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponse createProduct(ProductCreateRequest request) {

        if (productRepository.existsByProductName(request.productName())) {
            throw new DuplicateProductException(); // 우리가 만든 커스텀 예외
        }

        Product product = new Product();
        product.setProductName(request.productName());
        product.setProductPrice(request.productPrice());
        product.setRemainQuantity(request.remainQuantity());

        Product saveProduct = productRepository.save(product);

        return new ProductResponse(
                saveProduct.getProductId(),
                saveProduct.getProductName(),
                saveProduct.getProductPrice(),
                saveProduct.getRemainQuantity()
        );
    }

    @Transactional
    public AddStockResponse addStock( String productName, AddStockRequest request ) {
        Product product = productRepository.findByProductName(productName)
                .orElseThrow(ProductNotFoundException::new);

        product.setRemainQuantity(product.getRemainQuantity() + request.addQuantity());


        return new AddStockResponse(
                product.getProductName(),
                product.getRemainQuantity()
        );
    }

    // 3. 상품 삭제
    @Transactional
    public DeleteProductsResponse deleteProduct(String productName) {
        // 1. 삭제하기 전, 해당 상품이 실제로 존재하는지 existsByProductName로 검사
        if (!productRepository.existsByProductName(productName)) {
            throw new ProductNotFoundException();
        }

        // 2. 상품 삭제
        productRepository.deleteByProductName(productName);

        // 3. 남은 상품 목록 조회
        List<Product> remainProducts = productRepository.findAll();

        List<DeleteProductResponse> result = remainProducts.stream()
                .map(p -> new DeleteProductResponse(p.getProductName(), p.getRemainQuantity()))
                .toList(); // Java 16 이상이라면 .toList() 로 짧게 쓸 수 있습니다.

        return new DeleteProductsResponse(result);
    }

}
