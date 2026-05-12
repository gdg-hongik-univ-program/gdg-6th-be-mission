package gdg.hongik.mission.DTO;

import gdg.hongik.mission.Entity.Product;

public record ProductFindResponse (
        Long id,
        String name,
        int stock,
        int price
) {
    public static ProductFindResponse from(Product product) {
        return new ProductFindResponse(
                product.getId(),
                product.getName(),
                product.getStock(),
                product.getPrice());
    }
}
