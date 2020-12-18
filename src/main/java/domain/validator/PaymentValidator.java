package domain.validator;

import domain.Table;

public class PaymentValidator {
    private PaymentValidator() {
    }

    public static void checkTableIsUsed(Table table) {
        if (table.isEmpty()) {
            throw new IllegalArgumentException("빈 테이블은 계산할 수 없습니다.");
        }
    }
}
