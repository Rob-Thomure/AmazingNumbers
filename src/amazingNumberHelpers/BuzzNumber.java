package amazingNumberHelpers;

import java.util.function.Predicate;

public class BuzzNumber {
    private static final Predicate<Long> endsWith7 = num -> num % 10 == 7;
    private static final Predicate<Long> isDivisibleBy7 = num -> num % 7 == 0;
    public static Predicate<Long> isBuzzNumber = num -> endsWith7.or(isDivisibleBy7).test(num);
    public static Predicate<Long> isNotBuzzNumber = num -> endsWith7.or(isDivisibleBy7).negate().test(num);

    public static boolean isBuzzNumber(long num) {
        boolean numEndsWith7 = num % 10 == 7;
        boolean numIsDivisibleBy7 = num % 7 == 0;
        return numEndsWith7 || numIsDivisibleBy7;
    }

    public static boolean isNotBuzzNumber(long num) {
        return !isBuzzNumber(num);
    }


}
