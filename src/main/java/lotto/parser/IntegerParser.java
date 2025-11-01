package lotto.parser;

import java.util.regex.Pattern;

public class IntegerParser {

    private static final Pattern DIGIT_PATTERN = Pattern.compile("[0-9]+");

    private IntegerParser() {}

    public static int parseSingleInteger(String input) {
        validate(input);
        return Integer.parseInt(input);
    }

    private static void validate(String input) {
        if (!DIGIT_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("[ERROR] 입력은 정수여야 합니다.");
        }
    }
}
