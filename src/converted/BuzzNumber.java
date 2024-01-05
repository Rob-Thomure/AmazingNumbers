package converted;

import java.util.function.Predicate;

public class BuzzNumber {
    private static final Predicate<Long> endsWith7 = num -> num % 10 == 7;
    private static final Predicate<Long> isDivisibleBy7 = num -> num % 7 == 0;
    public static Predicate<Long> isBuzzNumber = num -> endsWith7.or(isDivisibleBy7).test(num);
    public static Predicate<Long> isNotBuzzNumber = num -> endsWith7.or(isDivisibleBy7).negate().test(num);


}
