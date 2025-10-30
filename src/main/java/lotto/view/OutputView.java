package lotto.view;

import lotto.domain.Lotto;
import lotto.dto.IssueLottoResponse;

public class OutputView {

    public static void printPurchaseAmountGuide() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printIssuedLottos(IssueLottoResponse response) {
        System.out.println();
        System.out.printf("%d개를 구매했습니다.", response.getCount());
        System.out.println();
        for (Lotto lotto: response.getLottos()) {
            System.out.println(lotto.getNumbers());
        }
    }
}
