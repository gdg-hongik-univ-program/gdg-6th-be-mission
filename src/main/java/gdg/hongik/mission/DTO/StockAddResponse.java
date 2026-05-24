package gdg.hongik.mission.DTO;

import gdg.hongik.mission.Entity.Product;

public record StockAddResponse (String name, int stock)
{
    public static StockAddResponse from(Product product) {
        return new StockAddResponse(product.getName(), product.getStock());
    }
}

