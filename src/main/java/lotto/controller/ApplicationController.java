package lotto.controller;

import lotto.service.ValidationService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ApplicationController {

    private final ValidationService validationService;

    public ApplicationController() {
        validationService = new ValidationService();
    }

    public void run() {
        // TODO 1: 로또 구입 금액을 입력받는다
        OutputView.printPurchaseAmountGuide();
        String input = InputView.validInput();
        validationService.validatePurchaseAmount(input);
    }
}
