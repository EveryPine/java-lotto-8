package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class BonusNumberTest {

    @Test
    void 보너스_번호가_최솟값_미만이면_예외가_발생한다() {
        // given
        int number = -5;

        // when

        // then
        assertThatThrownBy(() -> new BonusNumber(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스_번호가_최댓값_초과이면_예외가_발생한다() {
        // given
        int number = 50;

        // when

        // then
        assertThatThrownBy(() -> new BonusNumber(number))
                .isInstanceOf(IllegalArgumentException.class);
    }
}