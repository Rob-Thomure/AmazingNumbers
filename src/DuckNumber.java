import java.util.function.Predicate;

public class DuckNumber {
    private static final Predicate<String> doesNotLeadWithZero = numString -> !numString.startsWith("0");
    private static final Predicate<String> containsZero = numString -> numString.contains("0");
    public static Predicate<Long> isDuckNumber =
            num -> doesNotLeadWithZero.and(containsZero).test(Long.toString(num));
}
