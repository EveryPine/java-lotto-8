package lotto.dto;

import lotto.domain.Account;
import lotto.domain.IssuedLottos;

import static lotto.domain.Constants.LOTTO_PRICE;

public class IssueLottoRequest {

    private final int purchaseAmount;

    public IssueLottoRequest(Account account) {
        this.purchaseAmount = account.getPurchaseAmount();
    }

    public IssuedLottos toEntity() {
        return new IssuedLottos(purchaseAmount / LOTTO_PRICE);
    }
}
