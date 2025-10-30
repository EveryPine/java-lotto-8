package lotto.controller;

import lotto.domain.Lotto;
import lotto.dto.CreateWinningStatisticsRequest;
import lotto.dto.CreateWinningStatisticsResponse;
import lotto.dto.IssueLottoRequest;
import lotto.dto.IssueLottoResponse;
import lotto.parser.WinningNumbersParser;
import lotto.service.LottoService;
import lotto.service.ValidationService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class ApplicationController {

    private final ValidationService validationService;
    private final LottoService lottoService;

    public ApplicationController() {
        validationService = new ValidationService();
        lottoService = new LottoService();
    }

    public void run() {
        // TODO 1: 로또 구입 금액을 입력받는다
        OutputView.printPurchaseAmountGuide();
        String purchaseAmountInput = InputView.validInput();
        validationService.validatePurchaseAmount(purchaseAmountInput);

        // TODO 2: 로또 번호를 발행한다
        IssueLottoRequest issueLottoRequest = new IssueLottoRequest(Integer.parseInt(purchaseAmountInput));
        IssueLottoResponse issueLottoResponse = lottoService.issueLotto(issueLottoRequest);
        OutputView.printIssuedLottos(issueLottoResponse);

        // TODO 3: 보너스 번호가 포함된 당첨 번호를 입력받는다
        OutputView.printWinningNumbersGuide();
        String winningNumbersInput = InputView.validInput();
        validationService.validateWinningNumbers(winningNumbersInput);
        OutputView.printBonusNumberGuide();
        String bonusNumberInput = InputView.validInput();
        validationService.validateBonusNumber(bonusNumberInput);

        // TODO 4: 당첨 통계를 출력한다
        List<Lotto> issuedLottos = issueLottoResponse.getLottos();
        List<Integer> winningNumbers = WinningNumbersParser.parse(winningNumbersInput);
        int bonusNumber = Integer.parseInt(bonusNumberInput);
        CreateWinningStatisticsRequest createWinningStatisticsRequest =
                new CreateWinningStatisticsRequest(issuedLottos, winningNumbers, bonusNumber);
        CreateWinningStatisticsResponse createWinningStatisticsResponse =
                lottoService.createWinningStatistics(createWinningStatisticsRequest);
        OutputView.printWinningStatistics(createWinningStatisticsResponse);

    }
}
