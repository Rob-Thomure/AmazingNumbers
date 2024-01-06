package amazingNumberHelpers;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class JumpingNumber {

    private static BiPredicate<Long, Long> isMinusOne = (num1, num2) -> Math.abs(num1 - num2) == 1;

    public static Predicate<Long> isJumpingNumber = num -> {
        long trailingNum = num % 10;
        long newNum = num / 10;
        while (newNum > 0) {
            long nextTrailingNum = newNum % 10;
            if (Math.abs(trailingNum - nextTrailingNum) != 1) {
                return false;
            }

            trailingNum = nextTrailingNum;
            newNum /= 10;
        }
        return true;
    };

    public static Predicate<Long> isNotJumpingNumber = num -> isJumpingNumber.negate().test(num);

    public static boolean isJumpingNumber(long num) {
        long trailingNum = getLastDigit(num);
        long newNum = num / 10;
        while (newNum > 0) {
            long nextTrailingNum = getLastDigit(newNum);
            if (Math.abs(trailingNum - nextTrailingNum) != 1) {
                return false;
            }

            trailingNum = nextTrailingNum;
            newNum /= 10;
        }
        return true;
    };

    public static boolean isNotJumpingNumber(long num) {
        return !isJumpingNumber(num);
    }

    private static long getLastDigit(long num) {
        return num % 10;
    }
}
