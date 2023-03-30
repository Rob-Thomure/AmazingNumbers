import java.util.Scanner;
import java.util.function.Predicate;

public class Main {
    public static Predicate<Long> isNaturalNumber = num -> num > 0;
    public static Predicate<Long> isEven = num -> num % 2 == 0;
    public static Predicate<Long> isOdd = num -> isEven.negate().test(num);

    public static void main(String[] args) {
        long input = enterNumber();
        if (isNaturalNumber.test(input)) {
            System.out.printf("Properties of %d\n", input);
            System.out.printf("%14s%b\n", "even: ", isEven.test(input));
            System.out.printf("%14s%b\n", "odd: ", isOdd.test(input));
            System.out.printf("%14s%b\n", "buzz: ", BuzzNumber.isBuzzNumber.test(input));
            System.out.printf("%14s%b\n", "duck: ", DuckNumber.isDuckNumber.test(input));
        } else {
            System.out.println("This number is not natural!");
        }
    }

    public static long enterNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a natural number:");
        return scanner.nextLong();
    }
}

