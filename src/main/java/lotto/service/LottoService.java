package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.IssuedLottos;
import lotto.domain.Lotto;
import lotto.domain.WinningStatistics;
import lotto.dto.CreateWinningStatisticsRequest;
import lotto.dto.CreateWinningStatisticsResponse;
import lotto.dto.IssueLottoRequest;
import lotto.dto.IssueLottoResponse;

public class LottoService {

    public IssueLottoResponse issueLotto(IssueLottoRequest request) {
        IssuedLottos issuedLottos = request.toEntity();

        return IssueLottoResponse.from(issuedLottos);
    }

    public CreateWinningStatisticsResponse createWinningStatistics(CreateWinningStatisticsRequest request) {
        WinningStatistics winningStatistics = request.toEntity();

        return CreateWinningStatisticsResponse.from(winningStatistics);
    }
}
