package amazingNumberHelpers;

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
    public static Predicate<Long> isNotGapfulNumber = num -> hasAtLeast3Digits.and(isDivisible).negate().test(num);

    public static boolean isGapfulNumber(long num) {
        String numString = Long.toString(num);
        boolean numHasAtLeast3Digits = getDigitLength(num) >= 3;
        long firstDigitNum =  Long.parseLong(numString.substring(0, 1));
        long lastDigitNum = Long.parseLong(numString.substring(getDigitLength(num) - 1));
        long firstDigitConcatLastDigit = (firstDigitNum * 10) + lastDigitNum;
        boolean numIsDivisibleByFirstDigitConcatLastDigit = num % firstDigitConcatLastDigit == 0;
        return numHasAtLeast3Digits && numIsDivisibleByFirstDigitConcatLastDigit;
    }

    public static boolean isNotGapfulNumber(long num) {
        return !isGapfulNumber(num);
    }

    private static int getDigitLength(long num) {
        return Long.toString(num).length();
    }
}





