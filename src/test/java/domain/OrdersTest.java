package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrdersTest {

    @Test
    void addOrder() {
        Table table = TableRepository.tables().get(0);

        assertThrows(IllegalArgumentException.class, () -> {
            table.addOrder(MenuRepository.menus().get(0), 99);
            table.addOrder(MenuRepository.menus().get(0), 1);
        });
    }
}