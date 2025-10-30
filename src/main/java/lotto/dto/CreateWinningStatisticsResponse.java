package lotto.dto;

import lotto.domain.LottoRank;
import lotto.domain.WinningStatistics;

import java.util.Map;

public class CreateWinningStatisticsResponse {

    private final Map<LottoRank, Integer> winningCounts;
    private final double returnRate;

    private CreateWinningStatisticsResponse(Map<LottoRank, Integer> winningCounts, double returnRate) {
        this.winningCounts = winningCounts;
        this.returnRate = returnRate;
    }

    public static CreateWinningStatisticsResponse from(WinningStatistics winningStatistics) {
        return new CreateWinningStatisticsResponse(
                winningStatistics.getWinningCounts(),
                winningStatistics.getReturnRate());
    }

    public Map<LottoRank, Integer> getWinningCounts() {
        return winningCounts;
    }

    public double getReturnRate() {
        return returnRate;
    }
}
