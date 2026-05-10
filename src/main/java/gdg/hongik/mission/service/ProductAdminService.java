package gdg.hongik.mission.service;

import gdg.hongik.mission.entity.Product;
import gdg.hongik.mission.repository.ProductRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductAdminService {
        private final ProductRepository productRepository;

    public ProductAdminService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Object createProduct( Map<String, Object> request) {

        String productName = (String) request.get("productName");
        List<Product> products = productRepository.findAll();
        // 중복 검사
        for (Product p : products) {
            if (p.getProductName().equals(productName)) {
                Map<String, Object> error = new HashMap<>();
                error.put("error", "상품이 이미 존재합니다");
                return error;
            }
        }

        Product product = new Product();
        product.setProductName(productName);
        product.setProductPrice((int) request.get("productPrice"));
        product.setRemainQuantity((int) request.get("remainQuantity"));

        products.add(product);

        return product;
    }

    public Map<String, Object> addStock( Long productId, Map<String, Object> request ) {

        int addQuantity = (int) request.get("addQuantity");
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

        Map<String, Object> response = new HashMap<>();
        response.put("productName", product.getProductName());
        response.put("remainQuantity", product.getRemainQuantity());

        return response;
    }

    // 3. 상품 삭제
    public Map<String, Object> deleteProducts( Map<String, Object> request) {

        List<Long> productIds = (List<Long>) request.get("productIds");
        List<Product> products = productRepository.findAll();
        
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);

            if (productIds.contains(p.getProductId())) {
               products.remove(i);
                i--; // 인덱스 보정
            }
        }

        List<Map<String, Object>> result = new ArrayList<>();

        for (Product p : products) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("productName", p.getProductName());
            temp.put("remainQuantity", p.getRemainQuantity());
            result.add(temp);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("products", result);

        return response;
    }

}
