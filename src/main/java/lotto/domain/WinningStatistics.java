package lotto.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinningStatistics {

    private static final int LOTTO_PRICE = 1000;

    private final List<Lotto> issuedLottos;
    private final List<Integer> winningNumbers;
    private final int bonusNumber;
    private final Map<LottoRank, Integer> winningCounts;
    private double returnRate;

    public WinningStatistics(List<Lotto> issuedLottos, List<Integer> winningNumbers, int bonusNumber) {
        this.issuedLottos = issuedLottos;
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
        this.winningCounts = new HashMap<>();
        this.returnRate = 0.0;
    }

    public Map<LottoRank, Integer> getWinningCounts() {
        return Map.copyOf(winningCounts);
    }

    public double getReturnRate() {
        return returnRate;
    }

    public void calculateWinningCounts() {
        for (LottoRank lottoRank : LottoRank.values()) {
            winningCounts.put(lottoRank, 0);
            updateWinningCounts(lottoRank);
        }
    }

    public void calculateReturnRate() {
        int scale = 10;
        long expenses = (long) LOTTO_PRICE * issuedLottos.size();
        long income = 0;

        for (LottoRank lottoRank : LottoRank.values()) {
            income += lottoRank.getPrize() * winningCounts.get(lottoRank);
        }

        returnRate = Math.round((float) income / expenses * 100 * scale) / (double) scale;
    }

    private void updateWinningCounts(LottoRank lottoRank) {
        for (Lotto lotto : issuedLottos) {
            if (isSatisfied(lotto, lottoRank)) {
                winningCounts.put(lottoRank, winningCounts.get(lottoRank) + 1);
            }
        }
    }

    private boolean isSatisfied(Lotto lotto, LottoRank lottoRank) {
        int matchCount = getMatchCount(lotto);
        boolean satisfied = true;
        boolean bonusNumberMatch = lottoRank.requireBonusNumberMatch();

        if (matchCount != lottoRank.getRequiredMatchCount()) {
            satisfied = false;
        }

        if (bonusNumberMatch && !containsBonusNumber(lotto)) {
            satisfied = false;
        }

        return satisfied;
    }

    private int getMatchCount(Lotto lotto) {
        int matchCount = 0;

        for (int number: lotto.getNumbers()) {
            if (winningNumbers.contains(number)) {
                matchCount++;
            }
        }

        return matchCount;
    }

    private boolean containsBonusNumber(Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber);
    }
}
