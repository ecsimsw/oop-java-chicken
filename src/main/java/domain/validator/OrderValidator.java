package domain.validator;

public class OrderValidator {
    private OrderValidator() {
    }

    public static void checkValidQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("수량은 0이하일 수 없습니다.");
        }
    }
}
