package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

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
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);
        WinningStatistics actual = new WinningStatistics(issuedLottos, winningNumbers, bonusNumber);

        // when
        actual.calculateWinningCounts();
        actual.calculateReturnRate();

        // then
        assertThat(actual.getWinningCounts().get(LottoRank.FIRST)).isEqualTo(0);
        assertThat(actual.getWinningCounts().get(LottoRank.SECOND)).isEqualTo(0);
        assertThat(actual.getWinningCounts().get(LottoRank.THIRD)).isEqualTo(0);
        assertThat(actual.getWinningCounts().get(LottoRank.FOURTH)).isEqualTo(0);
        assertThat(actual.getWinningCounts().get(LottoRank.FIFTH)).isEqualTo(1);

        assertThat(actual.getReturnRate()).isEqualTo(62.5);
    }
}