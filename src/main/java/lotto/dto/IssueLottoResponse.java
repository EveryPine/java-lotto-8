package lotto.dto;

import lotto.domain.IssuedLottos;
import lotto.domain.Lotto;

import java.util.List;

public class IssueLottoResponse {

    private final int count;
    private final List<Lotto> lottos;

    private IssueLottoResponse(int count, List<Lotto> lottos) {
        this.count = count;
        this.lottos = lottos;
    }

    public int getCount() {
        return count;
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public static IssueLottoResponse from(IssuedLottos issuedLottos) {
        return new IssueLottoResponse(
                issuedLottos.getCount(),
                issuedLottos.getLottos());
    }

}
