package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.domain.Constants.LOTTO_PRICE;
import static lotto.domain.LottoRank.*;

public class WinningStatistics {

    private final List<Lotto> issuedLottos;
    private final WinningNumbers winningNumbers;
    private final Map<LottoRank, Integer> winningCounts;
    private double returnRate;

    public WinningStatistics(List<Lotto> issuedLottos, WinningNumbers winningNumbers) {
        this.issuedLottos = issuedLottos;
        this.winningNumbers = winningNumbers;
        this.winningCounts = new HashMap<>();
        this.returnRate = 0.0;
        initWinningCounts();
        initReturnRate();
    }

    public Map<LottoRank, Integer> getWinningCounts() {
        return Map.copyOf(winningCounts);
    }

    public double getReturnRate() {
        return returnRate;
    }

    private void initWinningCounts() {
        for (LottoRank lottoRank : LottoRank.values()) {
            winningCounts.put(lottoRank, 0);
        }

        for (Lotto lotto : issuedLottos) {
            LottoRank lottoRank = calculateLottoRank(lotto);
            winningCounts.put(lottoRank, winningCounts.get(lottoRank) + 1);
        }
    }

    private void initReturnRate() {
        final long scale = 10;
        long expenses = (long) LOTTO_PRICE * issuedLottos.size();
        long income = 0;

        for (LottoRank lottoRank : LottoRank.values()) {
            income += lottoRank.getPrize() * winningCounts.get(lottoRank);
        }

        returnRate = Math.round((float) income / expenses * 100 * scale) / (double) scale;
    }

    private LottoRank calculateLottoRank(Lotto lotto) {
        LottoRank resultRank = LottoRank.LOSE;
        List<LottoRank> lottoRankOrder = List.of(FIRST, SECOND, THIRD, FOURTH, FIFTH);

        for (LottoRank lottoRank : lottoRankOrder) {
            if (satisfiesMatchCountCondition(lotto, lottoRank) &&
                    satisfiesBonusNumberCondition(lotto, lottoRank)) {
                resultRank = lottoRank;
                break;
            }
        }

        return resultRank;
    }

    private boolean satisfiesMatchCountCondition(Lotto lotto, LottoRank lottoRank) {
        int matchCount = 0;
        Lotto winningLotto = winningNumbers.getWinningLotto();

        for (int number: lotto.getNumbers()) {
            if (winningLotto.getNumbers().contains(number)) {
                matchCount++;
            }
        }

        if (matchCount != lottoRank.getRequiredMatchCount()) {
            return false;
        }

        return true;
    }

    private boolean satisfiesBonusNumberCondition(Lotto lotto, LottoRank lottoRank) {
        if (!lottoRank.requireBonusNumberMatch()) {
            return true;
        }

        for (int number: lotto.getNumbers()) {
            if (number == winningNumbers.getBonusNumber()) {
                return true;
            }
        }

        return false;
    }
}
