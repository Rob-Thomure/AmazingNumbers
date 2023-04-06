import java.util.function.Function;
import java.util.function.Predicate;

public class PerfectSquareNumber {

    private static Function<Double, Double> squareRoot = Math::sqrt;
    private static Function<Double, Double> roundDown = Math::floor;
    public static Predicate<Long> isPerfectSquare =
            num -> squareRoot.apply((double) num) - squareRoot.andThen(roundDown).apply((double) num) == 0;
}
