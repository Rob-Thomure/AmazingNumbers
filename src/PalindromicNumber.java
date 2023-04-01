import java.util.function.*;

public class PalindromicNumber {
    private static final Function<Long, String> reverse = num -> new StringBuilder(num.toString()).reverse().toString();
    public static Predicate<Long> isPalindromic = num -> num.toString().equals(reverse.apply(num));
}
