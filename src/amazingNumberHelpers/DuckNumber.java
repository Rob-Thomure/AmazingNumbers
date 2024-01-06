package amazingNumberHelpers;

import java.util.function.Predicate;

public class DuckNumber {
    private static final Predicate<String> doesNotLeadWithZero = numString -> !numString.startsWith("0");
    private static final Predicate<String> containsZero = numString -> numString.contains("0");
    public static Predicate<Long> isDuckNumber =
            num -> doesNotLeadWithZero.and(containsZero).test(Long.toString(num));
    public static Predicate<Long> isNotDuckNumber =
            num -> doesNotLeadWithZero.and(containsZero).negate().test(Long.toString(num));

    public static boolean isDuckNumber(long num) {
        String numString = Long.toString(num);
        boolean numDoesNotStartWithZero = !numString.startsWith("0");
        boolean numContainsZero = numString.contains("0");
        return numDoesNotStartWithZero && numContainsZero;
    }

    public static boolean isNotDuckNumber(long num) {
        return !isDuckNumber(num);
    }
}
