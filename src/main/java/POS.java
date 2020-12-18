import domain.Table;
import domain.TableRepository;
import view.InputView;
import view.OutputView;

import java.util.Arrays;
import java.util.List;

public class POS {
    private boolean isAppEnd = false;

    public POS(){ }

    public void run(){
        List<Table> tables = TableRepository.tables();
        do{
            printMainMenu();
            // TODO :: 선택 입력
        } while (!isAppEnd);
    }

    private void printMainMenu() {
        OutputView.println("##메인화면");
        Arrays.stream(Menu.values())
                .map(Menu::getMenuName)
                .forEach(OutputView::println);
    }

    private Menu selectMenu() {
        String userInput = InputView.getMainMenu();
        return Menu.getSelectedMenu(userInput);
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
