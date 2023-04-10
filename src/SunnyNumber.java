import java.util.function.Function;
import java.util.function.Predicate;

public class SunnyNumber {
    private static Function<Long, Long> plusOne = num -> num + 1;
    public static Predicate<Long> isSunnyNumber = num ->
            PerfectSquareNumber.isPerfectSquare.test(plusOne.apply(num));
    public static Predicate<Long> isNotSunnyNumber = num -> isSunnyNumber.negate().test(num);
}
