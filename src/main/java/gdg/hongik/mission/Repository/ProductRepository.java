package gdg.hongik.mission.Repository;


import gdg.hongik.mission.Entity.Product;


public interface ProductRepository {

    Product findById(Long id);

    Product findByName(String name);

    void save(Product product);

    void deleteById(Long id);
}