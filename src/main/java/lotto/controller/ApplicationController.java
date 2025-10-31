package lotto.controller;

import lotto.domain.Account;
import lotto.domain.BonusNumber;
import lotto.domain.Lotto;
import lotto.dto.CreateWinningStatisticsRequest;
import lotto.dto.CreateWinningStatisticsResponse;
import lotto.dto.IssueLottoRequest;
import lotto.dto.IssueLottoResponse;
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
        Account account = getAccount();

        // TODO 2: 로또 번호를 발행하고, 그 결과를 출력한다
        IssueLottoResponse issueLottoResponse = issueLottos(account);
        printIssuedLottos(issueLottoResponse);

        // TODO 3: 보너스 번호가 포함된 당첨 번호를 입력받는다
        Lotto winningNumbers = getWinningNumbers();
        BonusNumber bonusNumber = getBonusNumber();

        // TODO 4: 당첨 통계를 생성하고, 그 결과를 출력한다
        CreateWinningStatisticsResponse createWinningStatisticsResponse =
                createWinningStatistics(
                        issueLottoResponse.getLottos(),
                        winningNumbers,
                        bonusNumber);
        printWinningStatistics(createWinningStatisticsResponse);
    }

    private Account getAccount() {
        Account account;

        OutputView.printPurchaseAmountGuide();
        while (true) {
            try {
                String purchaseAmountInput = InputView.validInput();
                account = new Account(Integer.parseInt(purchaseAmountInput));
                break;
            } catch (NumberFormatException e) {
                OutputView.printErrorMessage("[ERROR] 로또 구입 금액은 숫자여야 합니다.");
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

    private Lotto getWinningNumbers() {
        Lotto winningNumbers;

        OutputView.printWinningNumbersGuide();
        while (true) {
            try {
                String winningNumbersInput = InputView.validInput();
                List<String> parsed =  WinningNumbersParser.parse(winningNumbersInput);
                winningNumbers = new Lotto(parsed.stream()
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .toList());
                break;
            } catch (NumberFormatException e) {
                OutputView.printErrorMessage("[ERROR] 당첨 번호는 숫자로 이루어져야 합니다.");
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }

        return winningNumbers;
    }

    private BonusNumber getBonusNumber() {
        BonusNumber bonusNumber;

        OutputView.printBonusNumberGuide();
        while (true) {
            try {
                String bonusNumberInput = InputView.validInput();
                bonusNumber = new BonusNumber(Integer.parseInt(bonusNumberInput));
                break;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }

        return bonusNumber;
    }

    private CreateWinningStatisticsResponse createWinningStatistics(
            List<Lotto> issuedLottos, Lotto winningNumbers, BonusNumber bonusNumber
    ) {
        CreateWinningStatisticsRequest createWinningStatisticsRequest =
                new CreateWinningStatisticsRequest(issuedLottos, winningNumbers, bonusNumber);

        return lottoService.createWinningStatistics(createWinningStatisticsRequest);
    }

    private void printWinningStatistics(CreateWinningStatisticsResponse response) {
        OutputView.printWinningStatistics(response);
    }
}
