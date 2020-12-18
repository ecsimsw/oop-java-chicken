package controller;

import domain.Category;
import domain.MenuRepository;
import domain.Table;
import domain.TableRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentControllerTest {

    @Test
    void pay() {
        Table table = TableRepository.tables().get(0);
        table.addOrder(MenuRepository.menus().get(0), 10);

        int totalPrice = table.getOrderAmount();
        int ordered = table.getOrderQuantityInCategory(Category.CHICKEN);
        int satisfactionCount = ordered / 10;
        totalPrice -= (satisfactionCount * 10000);

        assertNotEquals(totalPrice, MenuRepository.menus().get(0).getPrice());
    }
}