package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.dto.CreateWinningStatisticsResponse;
import lotto.dto.IssueLottoResponse;

import java.text.DecimalFormat;

public class OutputView {

    public static void printPurchaseAmountGuide() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printIssuedLottos(IssueLottoResponse response) {
        System.out.printf("%n%d개를 구매했습니다.%n", response.getCount());
        for (Lotto lotto: response.getLottos()) {
            System.out.println(lotto.getNumbers());
        }
    }

    public static void printWinningNumbersGuide() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
    }

    public static void printBonusNumberGuide() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
    }

    public static void printWinningStatistics(CreateWinningStatisticsResponse response) {
        DecimalFormat prizeFormat = new DecimalFormat("###,###");

        System.out.println("\n당첨 통계\n---");
        for (LottoRank lottoRank: LottoRank.values()) {
            String matchingGuide = String.format("%d개 일치", lottoRank.getRequiredMatchCount());
            if (lottoRank.requireBonusNumberMatch()) {
                matchingGuide += ", 보너스 볼 일치";
            }
            System.out.printf("%s (%s원) - %d개%n",
                    matchingGuide, prizeFormat.format(lottoRank.getPrize()), response.getWinningCounts().get(lottoRank));
        }
        System.out.printf("총 수익률은 %.1f%%입니다.%n", response.getReturnRate());
    }
}
