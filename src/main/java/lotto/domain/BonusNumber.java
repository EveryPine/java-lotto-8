package lotto.domain;

import static lotto.domain.Constants.MAX_LOTTO_NUMBER;
import static lotto.domain.Constants.MIN_LOTTO_NUMBER;

public class BonusNumber {

    private final int number;

    public BonusNumber(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(String.format("[ERROR] 보너스 번호는 %d부터 %d 사이의 숫자여야 합니다.",
                    MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
        }
    }
}
