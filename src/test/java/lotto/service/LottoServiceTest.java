package lotto.service;

import lotto.domain.Lotto;
import lotto.dto.IssueLottoRequest;
import lotto.dto.IssueLottoResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoServiceTest {

    static LottoService lottoService;

    @BeforeAll
    static void setUp() {
        lottoService = new LottoService();
    }

    @Test
    void 로또_발행_테스트() {
        // given
        IssueLottoRequest request = new IssueLottoRequest(5000);

        // when
        IssueLottoResponse actual = lottoService.issueLotto(request);

        // then
        assertThat(actual.getCount()).isEqualTo(5);
        for (Lotto lotto : actual.getLottos()) {
            assertThat(lotto.getNumbers())
                    .isSorted()
                    .doesNotHaveDuplicates();
        }

    }
}