package amazingNumberHelpers;

import java.util.function.Function;
import java.util.function.Predicate;

public class PerfectSquareNumber {

    private static Function<Double, Double> squareRoot = Math::sqrt;
    private static Function<Double, Double> roundDown = Math::floor;
    public static Predicate<Long> isPerfectSquare =
            num -> squareRoot.apply((double) num) - squareRoot.andThen(roundDown).apply((double) num) == 0;
    public static Predicate<Long> isNotPerfectSquare = num -> isPerfectSquare.negate().test(num);

    public static boolean isPerfectSquare(long num) {
        double sqrt = Math.sqrt(num);
        double roundDown = Math.floor(sqrt);
        return  roundDown == 0;
    }

    public static boolean isNotPerfectSquare(long num) {
        return !isPerfectSquare(num);
    }
}
