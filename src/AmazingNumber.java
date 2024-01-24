import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class AmazingNumber {
    private final int num;
    private final Map<String, Boolean> properties;

    public AmazingNumber(int num) {
        this.num = num;
        this.properties = new LinkedHashMap<>();
        addProperties();
    }

    public int getNum() {
        return num;
    }

    public Map<String, Boolean> getProperties() {
        return properties;
    }

    private void addProperties() {
        properties.put("even", isEvenNumber());
        properties.put("odd", isOddNumber());
        properties.put("buzz", isBuzzNumber());
        properties.put("duck", isDuckNumber());
        properties.put("palindromic", isPalindromic());
        properties.put("gapful", isGapfulNumber());
        properties.put("spy", isSpyNumber());
        properties.put("square", isPerfectSquare(num));
        properties.put("sunny", isSunnyNumber());
        properties.put("jumping", isJumpingNumber());
        properties.put("happy", isHappyNumber());
        properties.put("sad", isSadNumber());
    }

    private boolean isEvenNumber() {
        return num % 2 == 0;
    }

    private boolean isOddNumber() {
        return !isEvenNumber();
    }

    public boolean isSadNumber() {
        return !isHappyNumber();
    }


    public boolean isBuzzNumber() {
        boolean numEndsWith7 = num % 10 == 7;
        boolean numIsDivisibleBy7 = num % 7 == 0;
        return numEndsWith7 || numIsDivisibleBy7;
    }

    public boolean isDuckNumber() {
        String numString = Long.toString(num);
        boolean numDoesNotStartWithZero = !numString.startsWith("0");
        boolean numContainsZero = numString.contains("0");
        return numDoesNotStartWithZero && numContainsZero;
    }

    public boolean isGapfulNumber() {
        String numString = Long.toString(num);
        boolean numHasAtLeast3Digits = getDigitLength() >= 3;
        long firstDigitNum =  Long.parseLong(numString.substring(0, 1));
        long lastDigitNum = Long.parseLong(numString.substring(getDigitLength() - 1));
        long firstDigitConcatLastDigit = (firstDigitNum * 10) + lastDigitNum;
        boolean numIsDivisibleByFirstDigitConcatLastDigit = num % firstDigitConcatLastDigit == 0;
        return numHasAtLeast3Digits && numIsDivisibleByFirstDigitConcatLastDigit;
    }

    public boolean isHappyNumber() {
        HashSet<Integer> hashSet = new HashSet<>();
        int tempNum = num;
        while (true) {
            tempNum = sumOfAllDigitsSquared(tempNum);
            if (tempNum == 1) {
                return true;
            }
            if (hashSet.contains(tempNum)) {
                return false;
            }
            hashSet.add(tempNum);
        }
    }



    public boolean isJumpingNumber() {
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
    }

    public boolean isPalindromic() {
        return num == reverseDigits();
    }

    public boolean isPerfectSquare(int num) {
        double sqrt = Math.sqrt(num);
        double roundDown = Math.floor(sqrt);
        return  roundDown == 0;
    }

    public boolean isSpyNumber() {
        return sumAllDigits() == multiplyAllDigits();
    }

    public boolean isSunnyNumber() {
        int numPlusOne = num + 1;
        return isPerfectSquare(numPlusOne);
    }

    private int getDigitLength() {
        return Long.toString(num).length();
    }

    private long getLastDigit(long num) {
        return num % 10;
    }

    private int sumOfAllDigitsSquared(int num) {
        return Arrays.stream(Integer.toString(num).split(""))
                .map(Integer::parseInt)
                .map(this::squared)
                .reduce(Integer::sum)
                .get();
    }

    private long reverseDigits() {
        return Long.parseLong(new StringBuilder(Long.toString(num)).reverse().toString());
    }

    private long sumAllDigits() {
        return Arrays.stream(Long.toString(num)
                        .split(""))
                .mapToLong(Long::parseLong)
                .sum();
    }

    private long multiplyAllDigits() {
        return Arrays.stream(Long.toString(num)
                        .split(""))
                .map(Long::parseLong)
                .reduce(1L, this::multiply);
    }

    private long multiply(long num1, long num2) {
        return num1 * num2;
    }

    private int squared(int num) {
        return num * num;
    }

    @Override
    public String toString() {
        return "AmazingNumber{" +
                "num=" + num +
                ", properties=" + properties +
                '}';
    }
}
