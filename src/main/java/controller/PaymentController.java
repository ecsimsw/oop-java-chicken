package controller;

import domain.Category;
import domain.PaymentMethod;
import domain.Table;
import domain.TableRepository;
import domain.validator.PaymentValidator;
import view.InputView;
import view.OutputView;

import java.util.List;

public class PaymentController {
    private static final Category DISCOUNT_CATEGORY = Category.CHICKEN;
    private static final int NUMBER_OF_ORDER_CONDITIONS = 10;
    private static final int MONEY_OF_DISCOUNT_BY_CATEGORY = 10000;
    private static final PaymentMethod DISCOUNT_PAYMENT_METHOD = PaymentMethod.MONEY;
    private static final int DISCOUNT_RATE_BY_METHOD = 5;

    private final List<Table> tables;
    private int totalPrice;

    public PaymentController(List<Table> tables) {
        this.tables = tables;
    }

    public void pay() {
        OutputView.printTables(tables);
        Table table = selectTableToPay();

        printOrderList(table);

        OutputView.noticePaymentProgress(table.getNumber());
        totalPrice = table.getOrderAmount();
        discount(table);
        OutputView.printTotalPrice(totalPrice);

        table.makeTableEmpty();
    }

    private Table selectTableToPay() {
        try {
            int tableNum = InputView.inputTableNumber();
            Table table = TableRepository.getTableByNumber(tableNum);
            PaymentValidator.checkTableIsUsed(table);
            return table;
        } catch (Exception e) {
            OutputView.printErrorMessage(e);
            return selectTableToPay();
        }
    }

    private void printOrderList(Table table) {
        OutputView.printBill(table.getBill());
    }

    private void discount(Table table) {
        discountByCategory(table);
        discountByPaymentMethod();
    }

    private void discountByCategory(Table table) {
        int ordered = table.getOrderQuantityInCategory(DISCOUNT_CATEGORY);
        int satisfactionCount = ordered / NUMBER_OF_ORDER_CONDITIONS;
        totalPrice -= (satisfactionCount * MONEY_OF_DISCOUNT_BY_CATEGORY);
    }

    private void discountByPaymentMethod() {
        PaymentMethod method = InputView.getPaymentMethod();
        if (method == DISCOUNT_PAYMENT_METHOD) {
            totalPrice = (100 - DISCOUNT_RATE_BY_METHOD) * totalPrice / 100;
        }
    }
}
