package amazingNumberHelpers;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.LongBinaryOperator;
import java.util.function.Predicate;

public class SpyNumber {
    private static final LongBinaryOperator multiply = (a, b) -> a * b;

    private static final Function<Long, Long> sum = num -> Arrays.stream(Long.toString(num)
                    .split(""))
                    .mapToLong(Long::parseLong)
                    .sum();

    private static final Function<Long, Long> product = num -> Arrays.stream(Long.toString(num)
                    .split(""))
                    .mapToLong(Long::parseLong)
                    .reduce(1, multiply);

    public static Predicate<Long> isSpyNumber = num -> Objects.equals(sum.apply(num), product.apply(num));
    public static Predicate<Long> isNotSpyNumber = num -> isSpyNumber.negate().test(num);

    public static boolean isSpyNumber(long num) {
        return sumAllDigits(num) == multiplyAllDigits(num);
    }

    public static boolean isNotSpyNumber(long num) {
        return !isSpyNumber(num);
    }

    private static long sumAllDigits(long num) {
        return Arrays.stream(Long.toString(num)
                        .split(""))
                .mapToLong(Long::parseLong)
                .sum();
    }

    private static long multiplyAllDigits(long num) {
        return Arrays.stream(Long.toString(num)
                        .split(""))
                .map(Long::parseLong)
                .reduce(1L, SpyNumber::multiply);
    }

    private static long multiply(long num1, long num2) {
        return num1 * num2;
    }

}
