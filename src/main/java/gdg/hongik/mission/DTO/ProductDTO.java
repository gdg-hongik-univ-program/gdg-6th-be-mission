package gdg.hongik.mission.DTO;

import gdg.hongik.mission.Entity.Product;

public record ProductDTO (
        Long id,
        String name,
        int stock,
        int price
){
    public static ProductDTO from(Product product){
        return new ProductDTO(product.getId(),
                product.getName(), product.getStock(), product.getPrice());
    }
}
