package lotto.controller;

import lotto.domain.Account;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.domain.WinningNumbers;
import lotto.dto.CreateWinningStatisticsRequest;
import lotto.dto.CreateWinningStatisticsResponse;
import lotto.dto.IssueLottoRequest;
import lotto.dto.IssueLottoResponse;
import lotto.parser.IntegerParser;
import lotto.parser.WinningNumbersParser;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class ApplicationController {

    private final LottoService lottoService;

    public ApplicationController() {
        lottoService = new LottoService();
    }

    public void run() {
        // TODO 1: 로또 구입 금액을 입력받는다
        Account account = inputAccount();

        // TODO 2: 로또 번호를 발행하고, 그 결과를 출력한다
        IssueLottoResponse issueLottoResponse = issueLottos(account);
        printIssuedLottos(issueLottoResponse);

        // TODO 3: 보너스 번호가 포함된 당첨 번호를 입력받는다
        WinningNumbers winningNumbers = inputWinningNumbers();

        // TODO 4: 당첨 통계를 생성하고, 그 결과를 출력한다
        CreateWinningStatisticsResponse createWinningStatisticsResponse =
                createWinningStatistics(
                        issueLottoResponse.getLottos(),
                        winningNumbers);
        printWinningStatistics(createWinningStatisticsResponse);
    }

    private Account inputAccount() {
        Account account;

        OutputView.printPurchaseAmountGuide();
        while (true) {
            try {
                String purchaseAmountInput = InputView.validInput();
                account = new Account(IntegerParser.parseSingleInteger(purchaseAmountInput));
                break;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }

        return account;
    }

    private IssueLottoResponse issueLottos(Account account) {
        IssueLottoRequest issueLottoRequest = new IssueLottoRequest(account);

        return lottoService.issueLotto(issueLottoRequest);
    }

    private void printIssuedLottos(IssueLottoResponse response) {
        OutputView.printIssuedLottos(response);
    }

    private WinningNumbers inputWinningNumbers() {
        Lotto winningLotto = inputWinningLotto();
        BonusNumber bonusNumber;
        WinningNumbers winningNumbers;

        OutputView.printBonusNumberGuide();
        while (true) {
            try {
                bonusNumber = inputBonusNumber();
                winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
                break;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }

        return winningNumbers;
    }

    private Lotto inputWinningLotto() {
        Lotto winningLotto;

        OutputView.printWinningNumbersGuide();
        while (true) {
            try {
                String winningNumbersInput = InputView.validInput();
                List<String> parsed = WinningNumbersParser.parse(winningNumbersInput);
                winningLotto = new Lotto(parsed.stream()
                        .mapToInt(IntegerParser::parseSingleInteger)
                        .boxed()
                        .toList());
                break;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }

        return winningLotto;
    }

    private BonusNumber inputBonusNumber() {
        BonusNumber bonusNumber;

        String bonusNumberInput = InputView.validInput();
        bonusNumber = new BonusNumber(IntegerParser.parseSingleInteger(bonusNumberInput));

        return bonusNumber;
    }

    private CreateWinningStatisticsResponse createWinningStatistics(
            List<Lotto> issuedLottos, WinningNumbers winningNumbers
    ) {
        CreateWinningStatisticsRequest createWinningStatisticsRequest =
                new CreateWinningStatisticsRequest(issuedLottos, winningNumbers);

        return lottoService.createWinningStatistics(createWinningStatisticsRequest);
    }

    private void printWinningStatistics(CreateWinningStatisticsResponse response) {
        OutputView.printWinningStatistics(response);
    }
}
