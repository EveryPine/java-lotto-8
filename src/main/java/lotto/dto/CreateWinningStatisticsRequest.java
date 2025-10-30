package lotto.dto;

import lotto.domain.Lotto;
import lotto.domain.WinningStatistics;

import java.util.List;

public class CreateWinningStatisticsRequest {

    private final List<Lotto> issuedLottos;
    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public CreateWinningStatisticsRequest(List<Lotto> issuedLottos, List<Integer> winningNumbers, int bonusNumber) {
        this.issuedLottos = issuedLottos;
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public WinningStatistics toEntity() {
        return new WinningStatistics(issuedLottos, winningNumbers, bonusNumber);
    }
}
