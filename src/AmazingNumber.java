import converted.HappyNumber;

import java.util.HashSet;
import java.util.function.Predicate;

public class AmazingNumber {

    public static boolean isBuzzNumber(long num) {
        return endsWith7(num) || isDivisibleBy7(num);
    }

    public static boolean isNotBuzzNumber(long num) {
        return !isBuzzNumber(num);
    }

    private static boolean endsWith7(long num) {
        return num % 10 == 7;
    }

    private static boolean isDivisibleBy7(long num) {
        return num % 7 == 0;
    }



    public static boolean isDuckNumber(long num) {
        return doesNotLeadWithZero(num) && containsZero(num);
    }

    public static boolean isNotDuckNumber(long num) {
        return !isDuckNumber(num);
    }

    private static boolean startsWithZero(long num) {
        return Long.toString(num).startsWith("0");
    }

    private static boolean doesNotLeadWithZero(long num) {
        return !startsWithZero(num);
    }

    private static boolean containsZero(long num) {
        return Long.toString(num).contains("0");
    }



    public static boolean isGapfulNumber(long num) {
        return hasAtLeast3Digits(num) && isDivisibleByConcatenationOfFirstAndLastDigits(num);
    }

    public static boolean isNotGapfulNumber(long num) {
        return !isGapfulNumber(num);
    }

    private static boolean hasAtLeast3Digits(long num) {
        return getNumDigits(num) >= 3;
    }

    private static long getFirstDigit(long num) {
        return Long.parseLong(Long.toString(num).substring(0, 1));
    }

    private static long getLastDigit(long num) {
        return  Long.parseLong(Long.toString(num).substring(getNumDigits(num) - 1));
    }

    private static int getNumDigits(long num) {
        return Long.toString(num).length();
    }

    private static boolean isDivisibleByConcatenationOfFirstAndLastDigits(long num) {
        long firstAndLastDigit = getFirstAndLastDigit(num);
        return num % firstAndLastDigit == 0;
    }

    private static long getFirstAndLastDigit(long num) {
        long firstDigit = getFirstDigit(num);
        long lastDigit = getLastDigit(num);
        return (firstDigit * 10) + lastDigit;
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
        int squareSum = 0;
        while (num != 0) {
            squareSum += (num % 10) * (num % 10);
            num /= 10;
        }
        return squareSum;
    }



    public static boolean isSadNumber(long num) {
        return isNotHappyNumber(num);
    }

    public static boolean isNotSadNumber(long num) {
        return isHappyNumber(num);
    }





}
