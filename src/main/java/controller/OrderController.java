package controller;

import domain.Menu;
import domain.MenuRepository;
import domain.Table;
import domain.TableRepository;
import domain.validator.OrderValidator;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class OrderController {
    private final List<Table> tables;

    public OrderController(List<Table> tables) {
        this.tables = tables;
    }

    public void order() {
        try {
            OutputView.printTables(tables);
            Table table = selectTable();

            printMenuList();
            Menu menu = selectMenu();

            int quantity = getQuantity();
            table.addOrder(menu, quantity);
        } catch (Exception e) {
            OutputView.printErrorMessage(e);
            order();
        }
    }

    private Table selectTable() {
        try {
            int tableNum = InputView.inputTableNumber();
            return TableRepository.getTableByNumber(tableNum);
        } catch (Exception e) {
            OutputView.printErrorMessage(e);
            return selectTable();
        }
    }

    private void printMenuList() {
        List menus = MenuRepository.menus();
        OutputView.printMenus(menus);
    }

    private Menu selectMenu() {
        try {
            int menuNum = InputView.getFoodMenu();
            return MenuRepository.getMenuByNumber(menuNum);
        } catch (Exception e) {
            OutputView.printErrorMessage(e);
            return selectMenu();
        }
    }

    private int getQuantity() {
        try {
            int quantity = InputView.getQuantity();
            OrderValidator.checkValidQuantity(quantity);
            return quantity;
        } catch (Exception e) {
            OutputView.printErrorMessage(e);
            return getQuantity();
        }
    }
}
