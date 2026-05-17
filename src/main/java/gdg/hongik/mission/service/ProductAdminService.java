package gdg.hongik.mission.service;

import gdg.hongik.mission.dto.*;
import gdg.hongik.mission.entity.Product;
import gdg.hongik.mission.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;


public class ProductAdminService {
        private final ProductRepository productRepository;

    public ProductAdminService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

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
    public DeleteProductsResponse deleteProducts( DeleteProductRequest request) {

        List<Long> productIds =  request.productIds();
        List<Product> products = productRepository.findAll();
        
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);

            if (productIds.contains(p.getProductId())) {
               productRepository.delete(p);
                i--; // 인덱스 보정
            }
        }

        List<DeleteProductResponse> result = new ArrayList<>();

        for (Product p : products) {
            DeleteProductResponse temp = new DeleteProductResponse(p.getProductName(),p.getRemainQuantity());
            result.add(temp);
        }

        return new DeleteProductsResponse(result);
    }

}
