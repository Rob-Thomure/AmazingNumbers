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

}
