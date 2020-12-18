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
    }

    private Table selectTable() {
        int tableNum = InputView.inputTableNumber();
        return TableRepository.getTableByNumber(tableNum);
    }
}
