package amazingNumberHelpers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Predicate;

public class HappyNumber {

    public static Predicate<Long> isHappyNumber = num -> {
        HashSet<Long> hashSet = new HashSet<>();
        while (true) {
            num = numSquareSum(num);
            if (num == 1) {
                return true;
            }
            if (hashSet.contains(num)) {
                return false;
            }
            hashSet.add(num);
        }
    };

    public static Predicate<Long> isNotHappyNumber = num -> isHappyNumber.negate().test(num);



    private static long numSquareSum(long num) {
        int squareSum = 0;
        while (num != 0) {
            squareSum += (num % 10) * (num % 10);
            num /= 10;
        }
        return squareSum;
    }


    public static boolean isHappyNumber(long num) {
        HashSet<Long> hashSet = new HashSet<>();
        while (true) {
            num = sumOfAllDigitsSquared(num);
            if (num == 1) {
                return true;
            }
            if (hashSet.contains(num)) {
                return false;
            }
            hashSet.add(num);
        }
    }

    public static boolean isNotHappyNumber(long num) {
        return !isHappyNumber(num);
    }

    private static long sumOfAllDigitsSquared(long num) {
         return Arrays.stream(Long.toString(num)
                .split(""))
                .mapToLong(Long::parseLong)
                .map(HappyNumber::squared)
                .sum();
    }

    private static long squared(long num) {
        return num * num;
    }








}
