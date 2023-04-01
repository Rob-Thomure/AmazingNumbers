import java.util.Scanner;
import java.util.function.Predicate;

public class Main {
    public static Predicate<Long> isNaturalNumber = num -> num > 0;
    public static Predicate<Long> isEven = num -> num % 2 == 0;
    public static Predicate<Long> isOdd = num -> isEven.negate().test(num);

    public static void main(String[] args) {
        printWelcomeMessage();
        printInstructions();
        boolean exit = false;
        while (!exit) {
            long request = getRequestFromUser();
            if (request == 0) {
                exit = true;
            } else if (isNaturalNumber.test(request)) {
                printProperties(request);
            } else {
                System.out.println("The first parameter should be a natural number or zero.\n");
            }
        }
        System.out.println("Goodbye!");
    }

    public static void printProperties(long num) {
        //System.out.printf("Properties of %d\n", num);
        System.out.printf("Properties of %,d\n", num);
        System.out.printf("%14s%b\n", "even: ", isEven.test(num));
        System.out.printf("%14s%b\n", "odd: ", isOdd.test(num));
        System.out.printf("%14s%b\n", "buzz: ", BuzzNumber.isBuzzNumber.test(num));
        System.out.printf("%14s%b\n", "duck: ", DuckNumber.isDuckNumber.test(num));
        System.out.printf("%14s%b\n", "palindromic: ", PalindromicNumber.isPalindromic.test(num));
        System.out.println();
    }

    public static long getRequestFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a request: ");
        long request = scanner.nextLong();
        System.out.println();
        return request;
    }

    public static void printWelcomeMessage() {
        System.out.println("Welcome to Amazing Numbers!\n");
    }

    public static void printInstructions() {
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter 0 to exit.\n");
    }
}

