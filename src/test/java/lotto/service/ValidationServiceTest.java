package lotto.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ValidationServiceTest {

    static ValidationService validationService;

    @BeforeAll
    static void setUp() {
        validationService = new ValidationService();
    }

    @Test
    void 로또_구입_금액_검증_테스트() {
        // given
        String input = "10000";

        // when

        // then
        assertDoesNotThrow(() -> validationService.validatePurchaseAmount(input));
    }
    @Test
    void 로또_구입_금액이_숫자로_이루어져있지_않으면_예외가_발생한다() {
        // given
        String input = "3400#";

        // when

        // then
        assertThatThrownBy(() -> validationService.validatePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_구입_금액이_1000미만이면_예외가_발생한다() {
        // given
        String input = "0";

        // when

        // then
        assertThatThrownBy(() -> validationService.validatePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_구입_금액이_100000000초과이면_예외가_발생한다() {
        // given
        String input = "90000000000000000";

        // when

        // then
        assertThatThrownBy(() -> validationService.validatePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_구입_금액이_1000단위가_아니라면_예외가_발생한다() {
        // given
        String input = "9872";

        // when

        // then
        assertThatThrownBy(() -> validationService.validatePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨_번호_검증_테스트() {
        // given
        String input = "1,2,3,4,5,6";

        // when

        // then
        assertDoesNotThrow(() -> validationService.validateWinningNumbers(input));
    }

    @Test
    void 당첨_번호_구분자가_쉼표가_아니라면_예외가_발생한다() {
        // given
        String input = "1;2;3;4;5;6";

        // when

        // then
        assertThatThrownBy(() -> validationService.validateWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 당첨_번호가_숫자가_아니라면_예외가_발생한다() {
        // given
        String input = "1,2,3,a,5,6";

        // when

        // then
        assertThatThrownBy(() -> validationService.validateWinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}