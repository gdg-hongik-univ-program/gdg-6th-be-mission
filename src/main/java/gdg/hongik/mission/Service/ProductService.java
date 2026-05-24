package gdg.hongik.mission.Service;

import gdg.hongik.mission.Dto.*;

import java.util.List;

public interface ProductService {
    GetProductResponse getProduct(String name);
    String purchaseProducts(List<PurchaseRequest> requests);
    void createProduct(CreateProductRequest request);
    void addStock(AddStockRequest request);
    void deleteProducts(DeleteProductsRequest request);
}
