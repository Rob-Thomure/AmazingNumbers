import java.util.Arrays;
import java.util.HashSet;

public class AmazingNumber {

    public static boolean isBuzzNumber(long num) {
        boolean numEndsWith7 = num % 10 == 7;
        boolean numIsDivisibleBy7 = num % 7 == 0;
        return numEndsWith7 || numIsDivisibleBy7;
    }

    public static boolean isNotBuzzNumber(long num) {
        return !isBuzzNumber(num);
    }



    public static boolean isDuckNumber(long num) {
        String numString = Long.toString(num);
        boolean numDoesNotStartWithZero = !numString.startsWith("0");
        boolean numContainsZero = numString.contains("0");
        return numDoesNotStartWithZero && numContainsZero;
    }

    public static boolean isNotDuckNumber(long num) {
        return !isDuckNumber(num);
    }



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



    public static boolean isSadNumber(long num) {
        return isNotHappyNumber(num);
    }

    public static boolean isNotSadNumber(long num) {
        return isHappyNumber(num);
    }



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



    public static boolean isPalindromic(long num) {
        return num == reverseDigits(num);
    }

    public static boolean isNotPalindromic(long num) {
        return !isPalindromic(num);
    }



    public static boolean isPerfectSquare(long num) {
        double sqrt = Math.sqrt(num);
        double roundDown = Math.floor(sqrt);
        return  roundDown == 0;
    }

    public static boolean isNotPerfectSquare(long num) {
        return !isPerfectSquare(num);
    }



    public static boolean isSpyNumber(long num) {
        return sumAllDigits(num) == multiplyAllDigits(num);
    }

    public static boolean isNotSpyNumber(long num) {
        return !isSpyNumber(num);
    }



    public static boolean isSunnyNumber(long num) {
        long numPlusOne = num + 1;
        return isPerfectSquare(numPlusOne);
    }

    public static boolean isNotSunnyNumber(long num) {
        return !isSunnyNumber(num);
    }




    private static int getDigitLength(long num) {
        return Long.toString(num).length();
    }

    private static long getLastDigit(long num) {
        return  Long.parseLong(Long.toString(num).substring(getDigitLength(num) - 1));
    }

    private static long sumOfAllDigitsSquared(long num) {
        int squareSum = 0;
        while (num != 0) {
            squareSum += (num % 10) * (num % 10);
            num /= 10;
        }
        return squareSum;
    }

    private static long reverseDigits(long num) {
        return Long.parseLong(new StringBuilder(Long.toString(num)).reverse().toString());
    }

    private static long sumAllDigits(long num) {
        return Arrays.stream(Long.toString(num)
                        .split(""))
                .mapToLong(Long::parseLong)
                .sum();
    }

    private static long multiplyAllDigits(long num) {
        return Arrays.stream(Long.toString(num)
                        .split(""))
                .map(Long::parseLong)
                .reduce(1L, AmazingNumber::multiply);
    }

    private static long multiply(long num1, long num2) {
        return num1 * num2;
    }
}
