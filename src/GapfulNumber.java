import java.util.function.Function;
import java.util.function.Predicate;

public class GapfulNumber {
    private static final Predicate<Long> hasAtLeast3Digits = num -> num.toString().length() >= 3;

    private static final Function<Long, Long> getFirstDigit = num -> Long.parseLong(num.toString().substring(0, 1));

    private static final Function<Long, Long> getLastDigit =
            num -> Long.parseLong(num.toString().substring(num.toString().length() - 1));

    private static final Function<Long, Long> getFirstAndLastDigit =
            num -> (getFirstDigit.apply(num) * 10) + getLastDigit.apply(num);

    private static final Predicate<Long> isDivisible = num -> {
        long gapNum = getFirstAndLastDigit.apply(num);
        return num % gapNum == 0;
    };

    public static Predicate<Long> isGapfulNumber = num -> hasAtLeast3Digits.and(isDivisible).test(num);
}





