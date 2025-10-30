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
        String purchaseAmountInput = inputPurchaseAmount();

        // TODO 2: 로또 번호를 발행하고, 그 결과를 출력한다
        IssueLottoResponse issueLottoResponse = issueLottos(purchaseAmountInput);
        printIssuedLottos(issueLottoResponse);

        // TODO 3: 보너스 번호가 포함된 당첨 번호를 입력받는다
        String winningNumbersInput = inputWinningNumbers();
        String bonusNumberInput = inputBonusNumber();

        // TODO 4: 당첨 통계를 생성하고, 그 결과를 출력한다
        CreateWinningStatisticsResponse createWinningStatisticsResponse =
                createWinningStatistics(
                        issueLottoResponse.getLottos(),
                        WinningNumbersParser.parse(winningNumbersInput),
                        Integer.parseInt(bonusNumberInput));
        printWinningStatistics(createWinningStatisticsResponse);
    }

    private String inputPurchaseAmount() {
        OutputView.printPurchaseAmountGuide();
        String purchaseAmountInput;
        while (true) {
            try {
                purchaseAmountInput = InputView.validInput();
                validationService.validatePurchaseAmount(purchaseAmountInput);
                break;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }

        return purchaseAmountInput;
    }

    private IssueLottoResponse issueLottos(String purchaseAmountInput) {
        IssueLottoRequest issueLottoRequest = new IssueLottoRequest(Integer.parseInt(purchaseAmountInput));

        return lottoService.issueLotto(issueLottoRequest);
    }

    private void printIssuedLottos(IssueLottoResponse response) {
        OutputView.printIssuedLottos(response);
    }

    private String inputWinningNumbers() {
        OutputView.printWinningNumbersGuide();
        String winningNumbersInput;
        while (true) {
            try {
                winningNumbersInput = InputView.validInput();
                validationService.validateWinningNumbers(winningNumbersInput);
                break;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }

        return winningNumbersInput;
    }

    private String inputBonusNumber() {
        OutputView.printBonusNumberGuide();
        String bonusNumberInput;
        while (true) {
            try {
                bonusNumberInput = InputView.validInput();
                validationService.validateBonusNumber(bonusNumberInput);
                break;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }

        return bonusNumberInput;
    }

    private CreateWinningStatisticsResponse createWinningStatistics(
            List<Lotto> issuedLottos, List<Integer> winningNumbers, int bonusNumber
    ) {
        CreateWinningStatisticsRequest createWinningStatisticsRequest =
                new CreateWinningStatisticsRequest(issuedLottos, winningNumbers, bonusNumber);

        return lottoService.createWinningStatistics(createWinningStatisticsRequest);
    }

    private void printWinningStatistics(CreateWinningStatisticsResponse response) {
        OutputView.printWinningStatistics(response);
    }
}
