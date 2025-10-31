package lotto.dto;

import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.WinningStatistics;

import java.util.List;

public class CreateWinningStatisticsRequest {

    private final List<Lotto> issuedLottos;
    private final Lotto winningNumbers;
    private final BonusNumber bonusNumber;

    public CreateWinningStatisticsRequest(List<Lotto> issuedLottos, Lotto winningNumbers, BonusNumber bonusNumber) {
        this.issuedLottos = issuedLottos;
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public WinningStatistics toEntity() {
        return new WinningStatistics(issuedLottos, winningNumbers, bonusNumber);
    }
}
