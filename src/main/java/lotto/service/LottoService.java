package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.IssuedLottos;
import lotto.domain.Lotto;
import lotto.domain.WinningStatistics;
import lotto.dto.CreateWinningStatisticsRequest;
import lotto.dto.CreateWinningStatisticsResponse;
import lotto.dto.IssueLottoRequest;
import lotto.dto.IssueLottoResponse;

import java.util.Collections;
import java.util.List;

public class LottoService {

    private final int MIN_LOTTO_NUMBER = 1;
    private final int MAX_LOTTO_NUMBER = 45;
    private final int LOTTO_NUMBER_COUNT = 6;

    public IssueLottoResponse issueLotto(IssueLottoRequest request) {
        IssuedLottos issuedLottos = request.toEntity();

        for (int lottoIndex = 0; lottoIndex < issuedLottos.getCount(); lottoIndex++) {
            List<Integer> randomNumbers = Randoms.pickUniqueNumbersInRange(
                    MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, LOTTO_NUMBER_COUNT);
            Collections.sort(randomNumbers);
            issuedLottos.updateLottos(new Lotto(randomNumbers));
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
