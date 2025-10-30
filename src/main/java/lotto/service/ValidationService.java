package lotto.service;

public class ValidationService {

    private final int PURCHASE_AMOUNT_UNIT = 1000;
    private final int MIN_PURCHASE_AMOUNT = 1000;
    private final int MAX_PURCHASE_AMOUNT = 100000000;

    public void validatePurchaseAmount(String input) {
        int purchaseAmount;
        try {
            purchaseAmount = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 정수만 입력 가능합니다.");
        }

        if (purchaseAmount < MIN_PURCHASE_AMOUNT || purchaseAmount > MAX_PURCHASE_AMOUNT) {
            throw new IllegalArgumentException(String.format("[ERROR] 로또 구입 금액은 %d 이상 %d 이하의 정수여야 합니다.",
                    MIN_PURCHASE_AMOUNT, MAX_PURCHASE_AMOUNT));
        }

        if (purchaseAmount % PURCHASE_AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException(String.format("[ERROR] 로또 구입 금액은 %d 단위의 정수여야 합니다.",
                    PURCHASE_AMOUNT_UNIT));
        }
    }
}
