package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class WinningNumbersTest {

    @Test
    void 보너스_번호가_최솟값_미만이면_예외가_발생한다() {
        // given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = -5;

        // when

        // then
        assertThatThrownBy(() -> new WinningNumbers(winningLotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스_번호가_최댓값_초과이면_예외가_발생한다() {
        // given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 50;

        // when

        // then
        assertThatThrownBy(() -> new WinningNumbers(winningLotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외가_발생한다() {
        // given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 6;

        // when

        // then
        assertThatThrownBy(() -> new WinningNumbers(winningLotto, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스_번호가_포함된_당첨_번호_생성_테스트() {
        // given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonusNumber = 7;

        // when

        // then
        assertDoesNotThrow(() -> new WinningNumbers(winningLotto, bonusNumber));
    }
}