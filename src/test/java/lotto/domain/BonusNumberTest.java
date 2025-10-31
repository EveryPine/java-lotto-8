package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class BonusNumberTest {

    @Test
    void 보너스_번호가_1미만이면_예외가_발생한다() {
        // given
        int number = 0;

        // when

        // then
        assertThatThrownBy(() -> new BonusNumber(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스_번호가_45초과이면_예외가_발생한다() {
        // given
        int number = 50;

        // when

        // then
        assertThatThrownBy(() -> new BonusNumber(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스_번호_생성_테스트() {
        // given
        int number = 30;

        // when

        // then
        assertDoesNotThrow(() -> new BonusNumber(number));
    }
}