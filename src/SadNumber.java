import java.util.function.Predicate;

public class SadNumber {

    public static Predicate<Long> isSadNumber = num -> HappyNumber.isNotHappyNumber.test(num);
    public static Predicate<Long> isNotSadNumber = num -> HappyNumber.isHappyNumber.test(num);

    public static boolean isSadNumber(long num) {
        return HappyNumber.isNotHappyNumber(num);
    }
}
