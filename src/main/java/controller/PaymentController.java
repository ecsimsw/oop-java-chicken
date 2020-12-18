package controller;

import domain.Table;
import domain.TableRepository;
import view.InputView;
import view.OutputView;

import java.util.List;

public class PaymentController {
    private final List<Table> tables;

    public PaymentController(List<Table> tables) {
        this.tables = tables;
    }

    public void pay() {
        OutputView.printTables(tables);
        Table table = selectTableToPay();
        printOrderList(table);
    }

    private Table selectTableToPay() {
        int tableNum = InputView.inputTableNumber();
        Table table = TableRepository.getTableByNumber(tableNum);

        if (table.isEmpty()) {
            throw new IllegalArgumentException("빈 테이블 입니다.");
        }

        return table;
    }

    private void printOrderList(Table table) {
        OutputView.printBill(table.getBill());
    }
}
