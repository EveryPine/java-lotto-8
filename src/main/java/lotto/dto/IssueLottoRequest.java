package lotto.dto;

import lotto.domain.IssuedLottos;

import static lotto.domain.Constants.LOTTO_PRICE;

public class IssueLottoRequest {

    private final int purchaseAmount;

    public IssueLottoRequest(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public IssuedLottos toEntity() {
        return new IssuedLottos(purchaseAmount / LOTTO_PRICE);
    }
}
