package gdg.hongik.mission.service;

import gdg.hongik.mission.dto.*;
import gdg.hongik.mission.entity.Product;
import gdg.hongik.mission.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public AddStockResponse addStock( Long productId, AddStockRequest request ) {

        int addQuantity = (int) request.addQuantity();
        List<Product> products = productRepository.findAll();
        Product product = null;

        for (Product p : products) {
            if (p.getProductId().equals(productId)) {
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
    public DeleteProductsResponse deleteProducts( DeleteProductRequest request) {

        List<Long> productIds =  request.productIds();
        productRepository.deleteAllById(productIds);

        List<Product> remainProducts = productRepository.findAll();

        List<DeleteProductResponse> result = new ArrayList<>();

        for (Product p : remainProducts) {
            DeleteProductResponse temp = new DeleteProductResponse(p.getProductName(),p.getRemainQuantity());
            result.add(temp);
        }

        return new DeleteProductsResponse(result);
    }

}
