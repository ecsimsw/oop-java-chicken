package domain;

import java.util.HashMap;
import java.util.Map;

public class Orders {
    private static final String BILL_FORMAT = "%s %s %s\n";

    private final Map<Menu, Integer> orders;

    public Orders() {
        orders = new HashMap<>();
    }

    public void addOrder(Menu menu, int count) {
        int existingOrderQuantity = orders.getOrDefault(menu, 0);

        if (existingOrderQuantity + count > 99) {
            throw new IllegalArgumentException("한 테이블에서 주문할 수 있는 한 메뉴의 최대 수량은 99개입니다.");
        }

        orders.put(menu, existingOrderQuantity + count);
    }

    private String makeOrderInfo(Menu menu) {
        String name = menu.getName();
        int count = orders.get(menu);
        int price = menu.getPrice();
        return String.format(BILL_FORMAT, name, count, price);
    }

    public int getNumberOfCategory(Category category) {
        return orders.keySet()
                .stream()
                .filter(menu -> menu.isInCategory(category))
                .mapToInt(menu -> orders.get(menu))
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Menu menu : orders.keySet()) {
            sb.append(makeOrderInfo(menu));
        }
        return sb.toString();
    }
}
