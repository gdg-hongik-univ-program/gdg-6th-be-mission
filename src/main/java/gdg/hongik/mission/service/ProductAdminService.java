package gdg.hongik.mission.service;

import gdg.hongik.mission.dto.request.ProductCreateRequest;
import gdg.hongik.mission.dto.request.StockUpdateRequest;
import gdg.hongik.mission.dto.response.ProductResponse;
import gdg.hongik.mission.entity.Product;
import gdg.hongik.mission.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import gdg.hongik.mission.exception.ProductNotFoundException;
import gdg.hongik.mission.exception.DuplicateProductException;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductAdminService {

    private final ProductRepository productRepository;

    // 상품 등록
    public ProductResponse createProduct(
            ProductCreateRequest request
    ) {

        Product existingProduct =
                productRepository.findByName(request.name());

        if (existingProduct != null) {
            throw new DuplicateProductException();
        }

        Product product = new Product(
                request.name(),
                request.price(),
                request.stock()
        );

        Product savedProduct =
                productRepository.save(product);

        return toResponse(savedProduct);
    }

    // 재고 추가
    public ProductResponse addStock(
            Long id,
            StockUpdateRequest request
    ) {

        Product product = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        product.addStock(request.quantity());

        return toResponse(product);
    }

    // 여러 상품 삭제
    public List<ProductResponse> deleteProducts(
            List<Long> ids
    ) {

        List<Product> products =
                productRepository.findAllById(ids);

        productRepository.deleteAll(products);

        return productRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ProductResponse toResponse(Product product) {

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
    }
}


