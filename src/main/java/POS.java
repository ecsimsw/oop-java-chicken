import controller.OrderController;
import controller.PaymentController;
import domain.Table;
import domain.TableRepository;
import view.InputView;
import view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class POS {
    private boolean isAppEnd = false;
    private final OrderController orderController;
    private final PaymentController paymentController;

    public POS() {
        List<Table> tables = TableRepository.tables();
        orderController = new OrderController(tables);
        paymentController = new PaymentController(tables);
    }

    public void run() {
        do {
            printMainMenu();
            Menu selected = selectMenu();
            doNext(selected);
        } while (!isAppEnd);
    }

    private void printMainMenu() {
        OutputView.println("##메인화면");
        Arrays.stream(Menu.values())
                .map(Menu::getMenuName)
                .forEach(OutputView::println);
    }

    private Menu selectMenu() {
        try {
            String userInput = InputView.getMainMenu();
            return Menu.getSelectedMenu(userInput);
        } catch (Exception e) {
            OutputView.printErrorMessage(e);
            return selectMenu();
        }
    }

    private void doNext(Menu menu) {
        if (menu == Menu.ORDER) {
            orderController.order();
            return;
        }

        if (menu == Menu.PAY) {
            paymentController.pay();
            return;
        }

        if (menu == Menu.EXIT) {
            setAppEnd();
        }
    }

    private void setAppEnd() {
        isAppEnd = true;
    }

    private enum Menu {
        ORDER("1", "1- 주문등록"),
        PAY("2", "2- 결제하기"),
        EXIT("3", "3- 프로그램 종료");

        private String userInput;
        private String menuName;

        Menu(String userInput, String menuName) {
            this.userInput = userInput;
            this.menuName = menuName;
        }

        public String getMenuName() {
            return menuName;
        }

        public static Menu getSelectedMenu(String userInput) {
            return Arrays.stream(values())
                    .filter(menu -> menu.userInput.equals(userInput))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("잘못된 메뉴 입력입니다."));
        }
    }
}
