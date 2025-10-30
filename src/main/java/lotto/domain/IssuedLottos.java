package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class IssuedLottos {

    private final int count;
    private final List<Lotto> lottos;

    public IssuedLottos(int count) {
        this.count = count;
        this.lottos = new ArrayList<>();
    }

    public int getCount() {
        return count;
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }

    public void updateLottos(Lotto lotto) {
        lottos.add(lotto);
    }
}
