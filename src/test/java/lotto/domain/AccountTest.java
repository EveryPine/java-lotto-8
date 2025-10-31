package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AccountTest {

    @Test
    void 계좌_생성_테스트() {
        // given
        int purchaseAmount = 10000;

        // when

        // then
        assertDoesNotThrow(() -> new Account(purchaseAmount));
    }

    @Test
    void 구입_금액이_최솟값_미만이면_예외가_발생한다() {
        // given
        int purchaseAmount = -5000;

        // when

        // then
        assertThatThrownBy(() -> new Account(purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구입_금액이_최댓값_초과이면_예외가_발생한다() {
        // given
        int purchaseAmount = 150000000;

        // when

        // then
        assertThatThrownBy(() -> new Account(purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구입_금액이_로또_가격으로_나누어_떨어지지_않으면_예외가_발생한다() {
        // given
        int purchaseAmount = 300;

        // when

        // then
        assertThatThrownBy(() -> new Account(purchaseAmount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}