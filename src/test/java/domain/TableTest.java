package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {
    @Test
    void testTableEmpty() {
        Table table = TableRepository.tables().get(0);
        table.addOrder(MenuRepository.menus().get(0), 1);

        assertEquals(false, table.isEmpty());
        assertEquals(MenuRepository.menus().get(0).getPrice(), table.getOrderAmount());

        table.getBill();
    }
}