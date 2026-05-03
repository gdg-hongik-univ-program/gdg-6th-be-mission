package gdg.hongik.mission;

import gdg.hongik.mission.Controller.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductStore {
    public static List<Product> products = new ArrayList<>();
    public static Long sequence = 1L;

    // 이름으로 상품 찾기
    public static Product findByName(String name) {
        for (Product product : products) {
            if(product.getName().equals(name)){
                return product;
            }
        }
        return null;
    }

    // 상품 재고 감소시키기
    public static void editStock(Long id, int quantity) {

        int currentStock = products.get(id.intValue()-1).getStock();

        // 재고가 부족한 경우 처리
        if(currentStock < quantity) {
            throw new RuntimeException("상품의 재고가 부족합니다.");
        }

        // 재고가 충분할 경우 재고량 수정
        products.get(id.intValue()-1).setStock(currentStock-quantity);
    }

    // 상품 재고 늘리기
    public static void addStock(Long id, int quantity) {

        int currentStock = products.get(id.intValue()-1).getStock();

        // 재고 증가추가하기
        products.get(id.intValue()-1).setStock(currentStock+quantity);
    }

    // 상품 정보 등록
    public static void createProduct(Product product) {

        products.add(product);
        sequence++;
    }


    // 상품 삭제
    public static void deleteProduct(Long id) {

        //배열에서 id로 객체 찾아 지우기
        for (Product product : products) {
            if(product.getId()==id){
                products.remove(product);
            }
        }
    }
}
