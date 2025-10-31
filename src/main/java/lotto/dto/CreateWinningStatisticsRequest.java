package lotto.dto;

import lotto.domain.Lotto;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningStatistics;

import java.util.List;

public class CreateWinningStatisticsRequest {

    private final List<Lotto> issuedLottos;
    private final WinningNumbers winningNumbers;

    public CreateWinningStatisticsRequest(List<Lotto> issuedLottos, WinningNumbers winningNumbers) {
        this.issuedLottos = issuedLottos;
        this.winningNumbers = winningNumbers;
    }

    public WinningStatistics toEntity() {
        return new WinningStatistics(issuedLottos, winningNumbers);
    }
}
