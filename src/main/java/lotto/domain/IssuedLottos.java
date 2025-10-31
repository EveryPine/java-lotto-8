package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

import static lotto.domain.Constants.*;

public class IssuedLottos {

    private final int count;
    private final List<Lotto> lottos;

    public IssuedLottos(int count) {
        this.count = count;
        this.lottos = new ArrayList<>();
        initLottos();
    }

    public int getCount() {
        return count;
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }

    private void initLottos() {
        for (int lottoIndex = 0; lottoIndex < count; lottoIndex++) {
            List<Integer> randomNumbers = Randoms.pickUniqueNumbersInRange(
                    MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, LOTTO_NUMBER_COUNT);
            List<Integer> sortedRandomNumbers = randomNumbers.stream()
                            .sorted()
                                    .toList();
            lottos.add(new Lotto(sortedRandomNumbers));
        }
    }
}
