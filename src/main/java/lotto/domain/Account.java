package lotto.domain;

import static lotto.domain.Constants.*;
import static lotto.domain.Constants.LOTTO_PRICE;

public class Account {

    private final int purchaseAmount;

    public Account(int purchaseAmount) {
        validate(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    private void validate(int purchaseAmount) {
        if (purchaseAmount < MIN_ACCOUNT || purchaseAmount > MAX_ACCOUNT) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 구입 금액은 %d 이상 %d 이하의 정수여야 합니다.", MIN_ACCOUNT, MAX_ACCOUNT));
        }

        if (purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 구입 금액은 %d 으로 나누어 떨어지는 숫자여야 합니다.", LOTTO_PRICE));
        }
    }
}
