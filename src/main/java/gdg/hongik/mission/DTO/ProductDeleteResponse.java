package gdg.hongik.mission.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ProductDeleteResponse {

    private List<RemainProduct> remainProductList = new ArrayList<>();

    @Getter
    @NoArgsConstructor
    public class RemainProduct {

        private String name;
        private int stock;

        public RemainProduct(String name, int stock) {
            this.name = name;
            this.stock = stock;
            remainProductList.add(this);
        }
    }
}
