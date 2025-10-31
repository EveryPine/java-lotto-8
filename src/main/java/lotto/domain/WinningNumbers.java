package lotto.domain;

import static lotto.domain.Constants.MAX_LOTTO_NUMBER;
import static lotto.domain.Constants.MIN_LOTTO_NUMBER;

public class WinningNumbers {

    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningNumbers(Lotto winningLotto, int bonusNumber) {
        validate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private void validate(Lotto winningLotto, int bonusNumber) {
        if (bonusNumber < MIN_LOTTO_NUMBER || bonusNumber > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(String.format("[ERROR] 보너스 번호는 %d부터 %d 사이의 숫자여야 합니다.",
                    MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
        }

        if (winningLotto.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }
}
