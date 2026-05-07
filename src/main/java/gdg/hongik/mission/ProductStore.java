package gdg.hongik.mission;
import gdg.hongik.mission.entity.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductStore {

    public static List<Product> products = new ArrayList<>();  // product를 담는 리스트
    public static long sequence = 1L;
}
