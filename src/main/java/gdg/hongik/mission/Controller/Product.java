package gdg.hongik.mission.Controller;

import gdg.hongik.mission.ProductStore;

public class Product {
    private Long id;
    private String name;
    private int stock = 0;
    private int price;

    //생성자
    public Product(String name, int stock, int price) {
        this.price = price;
        this.name = name;
        this.stock = stock;
        this.id = ProductStore.sequence;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
