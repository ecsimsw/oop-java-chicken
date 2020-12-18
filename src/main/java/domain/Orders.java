package domain;

import java.util.HashMap;
import java.util.Map;

public class Orders {
    private final Map<Menu, Integer> orders;

    public Orders() {
        orders = new HashMap<>();
    }

    public void addOrder(Menu menu, int count) {
        int existingOrderQuantity = orders.getOrDefault(menu, 0);

        if (existingOrderQuantity + count > 99) {
            throw new IllegalArgumentException("한 테이블에서 주문할 수 있는 한 메뉴의 최대 수량은 99개입니다.");
        }

        orders.replace(menu, existingOrderQuantity + count);
    }
}
