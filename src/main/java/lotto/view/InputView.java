package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static String validInput() {
        String input = Console.readLine();
        validate(input);
        return input.trim();
    }

    private static void validate(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 입력값이 비어있습니다.");
        }

    }
}
