package controller;

import domain.Menu;
import domain.MenuRepository;
import domain.Table;
import domain.TableRepository;
import jdk.internal.util.xml.impl.Input;
import view.InputView;
import view.OutputView;

import java.util.List;

public class OrderController {
    private final List<Table> tables;

    public OrderController(List<Table> tables) {
        this.tables = tables;
    }

    public void order() {
        OutputView.printTables(tables);
        Table table = selectTable();

        printMenuList();
        Menu menu = selectMenu();
        int quantity = getQuantity();
    }

    private Table selectTable() {
        int tableNum = InputView.inputTableNumber();
        return TableRepository.getTableByNumber(tableNum);
    }

    private void printMenuList() {
        List menus = MenuRepository.menus();
        OutputView.printMenus(menus);
    }

    private Menu selectMenu() {
        int menuNum = InputView.getFoodMenu();
        return MenuRepository.getMenuByNumber(menuNum);
    }

    private int getQuantity() {
        return InputView.getQuantity();
    }
}
