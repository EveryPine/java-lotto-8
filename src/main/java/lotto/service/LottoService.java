package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.IssuedLottos;
import lotto.domain.Lotto;
import lotto.domain.WinningStatistics;
import lotto.dto.CreateWinningStatisticsRequest;
import lotto.dto.CreateWinningStatisticsResponse;
import lotto.dto.IssueLottoRequest;
import lotto.dto.IssueLottoResponse;

import java.util.List;

import static lotto.domain.Constants.*;

public class LottoService {

    public IssueLottoResponse issueLotto(IssueLottoRequest request) {
        IssuedLottos issuedLottos = request.toEntity();

        for (int lottoIndex = 0; lottoIndex < issuedLottos.getCount(); lottoIndex++) {
            List<Integer> createdNumbers = Randoms.pickUniqueNumbersInRange(
                    MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, LOTTO_NUMBER_COUNT);

            issuedLottos.updateLottos(new Lotto(createdNumbers));
        }

        return IssueLottoResponse.from(issuedLottos);
    }

    public CreateWinningStatisticsResponse createWinningStatistics(CreateWinningStatisticsRequest request) {
        WinningStatistics winningStatistics = request.toEntity();
        winningStatistics.calculateWinningCounts();
        winningStatistics.calculateReturnRate();

        return CreateWinningStatisticsResponse.from(winningStatistics);
    }
}
