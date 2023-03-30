import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BuzzNumber {
    private int num;
    Supplier<String> isEvenOrOdd2 = () -> "This number is ".concat(this.num % 2 == 0 ? "Even." : "Odd.");

    public BuzzNumber(int num) {
        this.num = num;
    }

    public void printIsOrIsNotBuzzNumber() {
        System.out.println("It " + isOrIsNotBuzzNumber.get() + " a Buzz number.");
    }

    public void printExplanation() {
        if (isDivisibleBy7Ver2.or(endsWith7Ver2).negate().test(num)){
            isNeitherDivisibleNorEndsWith7();
        } else if (isDivisibleBy7Ver2.and(endsWith7Ver2.negate()).test(num)) {
            printIsDivisibleBy7();
        } else if (endsWith7Ver2.and(isDivisibleBy7Ver2.negate()).test(num)) {
            printEndsWith7();
        } else {
            printIsDivisibleAndEndsWith7();
        }
    }

    private Supplier<String> isOrIsNotBuzzNumber = () -> isBuzzNumber() ? "is" : "is not";

    private boolean isBuzzNumber() {
        return endsWith7Ver2.or(isDivisibleBy7Ver2).test(this.num);
    }

    private Predicate<Integer> endsWith7Ver2 = num -> num % 10 == 7;

    private Predicate<Integer> isDivisibleBy7Ver2 = num -> num % 7 == 0;

    private void isNeitherDivisibleNorEndsWith7() {
        System.out.println(num + " is neither divisible by 7 nor does it end with 7.");
    }

    private void printIsDivisibleBy7() {
        System.out.println(num + " is divisible by 7.");
    }

    private void printEndsWith7() {
        System.out.println(num + " ends with 7.");
    }

    private void printIsDivisibleAndEndsWith7() {
        System.out.println(num + " is divisible by 7 and ends with 7.");
    }

    public void test() {
        System.out.println(isEvenOrOdd2.get());
        printIsOrIsNotBuzzNumber();
        System.out.println("Explanation");
        printExplanation();
    }

}
