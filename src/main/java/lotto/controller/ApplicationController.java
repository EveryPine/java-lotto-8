package lotto.controller;

import lotto.dto.IssueLottoRequest;
import lotto.dto.IssueLottoResponse;
import lotto.service.LottoService;
import lotto.service.ValidationService;
import lotto.view.InputView;
import lotto.view.OutputView;

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
        String input = InputView.validInput();
        validationService.validatePurchaseAmount(input);

        // TODO 2: 로또 번호를 발행한다
        IssueLottoRequest issueLottoRequest = new IssueLottoRequest(Integer.parseInt(input));
        IssueLottoResponse issueLottoResponse = lottoService.issueLotto(issueLottoRequest);
        OutputView.printIssuedLottos(issueLottoResponse);
    }
}
