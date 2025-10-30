package lotto.service;

import java.util.Arrays;
import java.util.List;

import static lotto.domain.Constants.*;

public class ValidationService {

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

        if (purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(String.format("[ERROR] 로또 구입 금액은 %d 단위의 정수여야 합니다.",
                    LOTTO_PRICE));
        }
    }

    public void validateWinningNumbers(String input) {
        final String delimiter = ",";
        List<Integer> winningNumbers;

        if (!input.contains(delimiter)) {
            throw new IllegalArgumentException(String.format("[ERROR] 당첨 번호는 \"%s\"로 구분되어야 합니다.",
                    delimiter));
        }

        try {
            winningNumbers = Arrays.stream(input.split(delimiter))
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 각 당첨 번호는 정수로 이루어져야 합니다.");
        }
    }

    public void validateBonusNumber(String input) {
        try {
            int bonusNumber =  Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 정수여야 합니다.");
        }
    }
}
