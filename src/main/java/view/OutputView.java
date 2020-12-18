package view;

import domain.Menu;
import domain.Table;

import java.util.List;

public class OutputView {
    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String EMPTY_BOTTOM_LINE = "└ ─ ┘";
    private static final String USED_BOTTOM_LINE = "└ # ┘";
    private static final String PAYMENT_PROGRESS_MESSAGE = "## %s번 테이블의 결제를 진행합니다.\n";
    private static final String ERROR_MESSAGE = "[Error] %s \n";

    public static void printErrorMessage(Exception e) {
        printf(ERROR_MESSAGE, e.getMessage());
    }

    public static void printTotalPrice(int totalPrice) {
        println("## 최종 결제할 금액");
        println(totalPrice);
    }

    public static void noticePaymentProgress(int tableNumber) {
        printf(PAYMENT_PROGRESS_MESSAGE, tableNumber);
    }

    public static void printBill(String bill) {
        println("## 주문 내역");
        println("메뉴 수량 금액");
        print(bill);
    }

    public static void printMenus(final List<Menu> menus) {
        for (final Menu menu : menus) {
            System.out.println(menu);
        }
    }

    public static void printTables(final List<Table> tables) {
        println("## 테이블 목록");
        printTop(tables.size());
        printTableNumbers(tables);
        printBottom(tables);
    }

    private static void printTop(final int count) {
        for (int i = 0; i < count; i++) {
            print(TOP_LINE);
        }
        System.out.println();
    }

    private static void printTableNumbers(final List<Table> tables) {
        for (final Table table : tables) {
            System.out.printf(TABLE_FORMAT, table);
        }
        System.out.println();
    }

    private static void printBottom(List<Table> tables) {
        tables.stream()
                .map(Table::isEmpty)
                .forEach(OutputView::markUsedTable);
    }

    private static void markUsedTable(boolean isEmpty) {
        if (isEmpty) {
            OutputView.print(EMPTY_BOTTOM_LINE);
            return;
        }
        OutputView.print(USED_BOTTOM_LINE);
    }

    public static void printf(String msg, Object... args) {
        System.out.printf(msg, args);
    }

    public static void println(Object msg) {
        System.out.println(msg);
    }

    public static void print(String msg) {
        System.out.print(msg);
    }
}
