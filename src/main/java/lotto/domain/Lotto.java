package lotto.domain;

import java.util.List;

import static lotto.domain.Constants.MAX_LOTTO_NUMBER;
import static lotto.domain.Constants.MIN_LOTTO_NUMBER;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }

        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 서로 중복될 수 없습니다.");
        }

        for (int number: numbers) {
            if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
                throw new IllegalArgumentException(String.format("[ERROR] 로또 번호는 %d부터 %d 사이의 숫자여야 합니다.",
                        MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
            }
        }
    }
}
