package gdg.hongik.mission.service;

import gdg.hongik.mission.dto.*;
import gdg.hongik.mission.entity.Product;
import gdg.hongik.mission.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

        String productName = (String) request.productName();
        List<Product> products = productRepository.findAll();
        // 중복 검사
        for (Product p : products) {
            if (p.getProductName().equals(productName)) {
                throw new RuntimeException("상품이 이미 존재합니다.");
            }
        }

        Product product = new Product();
        product.setProductName(productName);
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
        System.out.println("==========" + productName);
        int addQuantity = (int) request.addQuantity();
        List<Product> products = productRepository.findAll();
        Product product = null;

        for (Product p : products) {
            if (p.getProductName().equals(productName)) {
                product = p;
                break;
            }
        }

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        product.setRemainQuantity(product.getRemainQuantity() + addQuantity);


        return new AddStockResponse(
                product.getProductName(),
                product.getRemainQuantity()
        );
    }

    // 3. 상품 삭제
    @Transactional
    public DeleteProductsResponse deleteProduct(String productName) {

        // 1. 요청 값 확인 (가장 먼저 실행)
        System.out.println("프론트에서 넘어온 삭제할 상품명: " + productName);

        // 2. 상품 삭제
        productRepository.deleteByProductName(productName);

        // 3. 남은 상품 목록 조회
        List<Product> remainProducts = productRepository.findAll();

        List<DeleteProductResponse> result = remainProducts.stream()
                .map(p -> new DeleteProductResponse(p.getProductName(), p.getRemainQuantity()))
                .collect(Collectors.toList()); // Java 16 이상이라면 .toList() 로 짧게 쓸 수 있습니다.

        return new DeleteProductsResponse(result);
    }

}
