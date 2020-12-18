import domain.Table;
import domain.TableRepository;
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
        }while(!isAppEnd);
    }

    private void printMainMenu(){
        OutputView.println("##메인화면");
        Arrays.stream(Menu.values())
                .map(Menu::getMenuName)
                .forEach(OutputView::println);
    }

    private enum Menu {
        ORDER(1, "1- 주문등록"),
        PAY(2, "2- 결제하기"),
        EXIT(3, "3- 프로그램 종료");

        private int userInput;
        private String menuName;

        Menu(int userInput, String menuName){
            this.userInput= userInput;
            this.menuName = menuName;
        }

        public String getMenuName(){
            return menuName;
        }
    }
}
