package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.domain.LottoRank.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class WinningStatisticsTest {

    @Test
    void 당첨_통계_테스트() {
        // given
        List<Lotto> issuedLottos = List.of(
                new Lotto(List.of(8, 21, 23, 41, 42, 43)),
                new Lotto(List.of(3, 5, 11, 16, 32, 38)),
                new Lotto(List.of(7, 11, 16, 35, 36, 44)),
                new Lotto(List.of(1, 8, 11, 31, 41, 42)),
                new Lotto(List.of(13, 14, 16, 38, 42, 45)),
                new Lotto(List.of(7, 11, 30, 40, 42, 43)),
                new Lotto(List.of(2, 13, 22, 32, 38, 45)),
                new Lotto(List.of(1, 3, 5, 14, 22, 45))
        );
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);

        // when
        WinningStatistics actual = new WinningStatistics(issuedLottos, winningNumbers);

        // then
        assertThat(actual.getWinningCounts().get(FIRST)).isEqualTo(0);
        assertThat(actual.getWinningCounts().get(SECOND)).isEqualTo(0);
        assertThat(actual.getWinningCounts().get(THIRD)).isEqualTo(0);
        assertThat(actual.getWinningCounts().get(LottoRank.FOURTH)).isEqualTo(0);
        assertThat(actual.getWinningCounts().get(LottoRank.FIFTH)).isEqualTo(1);

        assertThat(actual.getReturnRate()).isEqualTo(62.5);
    }

    @Test
    void 당첨_횟수_1등상_테스트() {
        // given
        List<Lotto> issuedLottos = List.of(
                new Lotto(List.of(1, 3, 5, 14, 22, 45))
        );
        Lotto winningLotto = new Lotto(List.of(1, 3, 5, 14, 22, 45));
        BonusNumber bonusNumber = new BonusNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
        Map<LottoRank, Integer> expected = Map.of(
                FIRST, 1,
                SECOND, 0,
                THIRD, 0,
                FOURTH, 0,
                FIFTH, 0,
                LOSE, 0
        );

        // when
        WinningStatistics actual = new WinningStatistics(issuedLottos, winningNumbers);

        // then
        assertThat(actual).extracting("winningCounts")
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void 당첨_횟수_2등상_테스트() {
        // given
        List<Lotto> issuedLottos = List.of(
                new Lotto(List.of(1, 3, 5, 14, 22, 45))
        );
        Lotto winningLotto = new Lotto(List.of(1, 3, 5, 14, 22, 44));
        BonusNumber bonusNumber = new BonusNumber(45);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
        Map<LottoRank, Integer> expected = Map.of(
                FIRST, 0,
                SECOND, 1,
                THIRD, 0,
                FOURTH, 0,
                FIFTH, 0,
                LOSE, 0
        );

        // when
        WinningStatistics actual = new WinningStatistics(issuedLottos, winningNumbers);

        // then
        assertThat(actual).extracting("winningCounts")
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void 당첨_횟수_3등상_테스트() {
        // given
        List<Lotto> issuedLottos = List.of(
                new Lotto(List.of(1, 3, 5, 14, 22, 45))
        );
        Lotto winningLotto = new Lotto(List.of(1, 3, 5, 14, 22, 44));
        BonusNumber bonusNumber = new BonusNumber(40);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
        Map<LottoRank, Integer> expected = Map.of(
                FIRST, 0,
                SECOND, 0,
                THIRD, 1,
                FOURTH, 0,
                FIFTH, 0,
                LOSE, 0
        );

        // when
        WinningStatistics actual = new WinningStatistics(issuedLottos, winningNumbers);

        // then
        assertThat(actual).extracting("winningCounts")
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void 당첨_횟수_4등상_테스트() {
        // given
        List<Lotto> issuedLottos = List.of(
                new Lotto(List.of(1, 3, 5, 14, 22, 45))
        );
        Lotto winningLotto = new Lotto(List.of(1, 3, 5, 14, 23, 44));
        BonusNumber bonusNumber = new BonusNumber(40);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
        Map<LottoRank, Integer> expected = Map.of(
                FIRST, 0,
                SECOND, 0,
                THIRD, 0,
                FOURTH, 1,
                FIFTH, 0,
                LOSE, 0
        );

        // when
        WinningStatistics actual = new WinningStatistics(issuedLottos, winningNumbers);

        // then
        assertThat(actual).extracting("winningCounts")
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void 당첨_횟수_5등상_테스트() {
        // given
        List<Lotto> issuedLottos = List.of(
                new Lotto(List.of(1, 3, 5, 14, 22, 45))
        );
        Lotto winningLotto = new Lotto(List.of(1, 3, 5, 13, 23, 44));
        BonusNumber bonusNumber = new BonusNumber(40);
        WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);
        Map<LottoRank, Integer> expected = Map.of(
                FIRST, 0,
                SECOND, 0,
                THIRD, 0,
                FOURTH, 0,
                FIFTH, 1,
                LOSE, 0
        );

        // when
        WinningStatistics actual = new WinningStatistics(issuedLottos, winningNumbers);

        // then
        assertThat(actual).extracting("winningCounts")
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void 수익률_테스트() {
        // given
        List<Lotto> issuedLottos = List.of(
                new Lotto(List.of(8, 21, 23, 41, 42, 43)),
                new Lotto(List.of(3, 5, 11, 16, 32, 38)),
                new Lotto(List.of(7, 11, 16, 35, 36, 44)),
                new Lotto(List.of(1, 8, 11, 31, 41, 42)),
                new Lotto(List.of(13, 14, 16, 38, 42, 45)),
                new Lotto(List.of(7, 11, 30, 40, 42, 43)),
                new Lotto(List.of(2, 13, 22, 32, 38, 45)),
                new Lotto( List.of(1, 3, 5, 14, 22, 45))
        );
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);
        WinningNumbers winningNumbers =  new WinningNumbers(winningLotto, bonusNumber);
        double expected = 62.5;

        // when
        WinningStatistics actual = new WinningStatistics(issuedLottos, winningNumbers);

        // then
        assertThat(actual).extracting("returnRate")
                .isEqualTo(expected);
    }
}