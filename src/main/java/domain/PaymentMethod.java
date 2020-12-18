package domain;

import java.util.Arrays;

public enum PaymentMethod {
    CARD("1"),
    MONEY("2");

    private String userInput;

    PaymentMethod(String userInput) {
        this.userInput = userInput;
    }

    public static PaymentMethod getSelectedMethod(String userInput) {
        return Arrays.stream(values())
                .filter(menu -> menu.userInput.equals(userInput))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("적절한 결제 방식이 아닙니다."));
    }
}
