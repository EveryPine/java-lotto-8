package lotto.dto;

import lotto.domain.IssuedLottos;

public class IssueLottoRequest {

    private final int PURCHASE_AMOUNT_UNIT = 1000;

    private final int purchaseAmount;

    public IssueLottoRequest(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public IssuedLottos toEntity() {
        return new IssuedLottos(purchaseAmount / PURCHASE_AMOUNT_UNIT);
    }
}
