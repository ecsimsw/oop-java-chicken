package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputTableNumber() {
        System.out.println("## 주문할 테이블을 선택하세요.");
        return scanner.nextInt();
    }

    public static String getMainMenu() {
        OutputView.println("##원하는 기능을 선택하세요.");
        return getInput();
    }

    public static int getFoodMenu() {
        OutputView.println("##등록할 메뉴를 선택하세요.");
        return getInteger();
    }

    public static int getQuantity() {
        OutputView.println("##메뉴의 수량을 입력하세요.");
        return getInteger();
    }

    public static int getInteger() {
        return scanner.nextInt();
    }

    public static String getInput() {
        return scanner.nextLine();
    }
}
