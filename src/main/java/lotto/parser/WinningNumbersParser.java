package lotto.parser;

import java.util.Arrays;
import java.util.List;

public class WinningNumbersParser {

    private static final String DELIMITER = ",";

    public static List<String> parse(String input) {
        validate(input);

        return Arrays.stream(input.split(DELIMITER)).toList();
    }

    private static void validate(String input) {
        if (!input.contains(DELIMITER)) {
            throw new IllegalArgumentException(String.format("[ERROR] 당첨 번호는 \"%s\"으로 구분되어야 합니다.",
                    DELIMITER));
        }
    }
}
