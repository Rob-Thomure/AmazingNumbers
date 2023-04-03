import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.LongStream;

public class AppRunner {
    private final Predicate<String> isNumber = str -> str.matches("\\d+");
    private final Predicate<String> isNaturalNumber = num -> Long.parseLong(num) > 0;
    private final Predicate<Long> isEven = num -> num % 2 == 0;
    private final Predicate<Long> isOdd = num -> isEven.negate().test(num);

    public void runApp() {
        printWelcomeMessage();
        printInstructions();
        boolean exit = false;
        while (!exit) {
            List<String> request = getRequestFromUser();
            if (request.get(0).isEmpty()) {
                printInstructions();
            } else if (request.get(0).equals("0")) {
                exit = true;
            } else if (isNumber.negate().or(isNaturalNumber.negate()).test(request.get(0))) {
                System.out.println("The first parameter should be a natural number or zero.\n");
            } else if (request.size() == 2 && isNumber.negate().or(isNaturalNumber.negate()).test(request.get(1))) {
                System.out.println("The second parameter should be a natural number or zero.\n");
            }  else if (request.size() == 1) {
                printSingleNumProperties(Long.parseLong(request.get(0)));
            } else if (request.size() == 2) {
                printMultiNumProperties(Long.parseLong(request.get(0)), Long.parseLong(request.get(1)));
            } else if (request.size() == 3) {
                Predicate<Long> filter = getFilter(request.get(2));
                if (null != filter) {
                    printFilteredPropertyList(Long.parseLong(request.get(0)), Long.parseLong(request.get(1)), filter);
                } else {
                    System.out.printf("The property [%s] is wrong.\n" +
                            "Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD]\n\n"
                            , request.get(2).toUpperCase());
                }
            }
        }
        System.out.println("Goodbye!");
    }

    private void printFilteredPropertyList(Long num, Long occurrences, Predicate<Long> filter) {
        LongStream.iterate(num, i -> i + 1)
                .boxed()
                .filter(filter)
                .limit(occurrences)
                .forEach(this::printPropertyList);
        System.out.println();
    }

    private Predicate<Long> getFilter(String filter) {
        return switch (filter) {
            case "buzz" -> BuzzNumber.isBuzzNumber;
            case "duck" -> DuckNumber.isDuckNumber;
            case "palindromic" -> PalindromicNumber.isPalindromic;
            case "gapful" -> GapfulNumber.isGapfulNumber;
            case "spy" -> SpyNumber.isSpyNumber;
            case "even" -> isEven;
            case "odd" -> isOdd;
            default -> null;
        };
    }

    private void printPropertyList(Long num) {
        List<String> list = new ArrayList<>();
        if (BuzzNumber.isBuzzNumber.test(num)) list.add("buzz");
        if (DuckNumber.isDuckNumber.test(num)) list.add("duck");
        if (PalindromicNumber.isPalindromic.test(num)) list.add("palindromic");
        if (GapfulNumber.isGapfulNumber.test(num)) list.add("gapful");
        if (SpyNumber.isSpyNumber.test(num)) list.add("spy");
        if (isEven.test(num)) list.add("even");
        if (isOdd.test(num)) list.add("odd");
        System.out.printf("%14d is %s\n", num, String.join(", ", list));
    }

    private void printMultiNumProperties(Long startingNum, Long consecutiveNums) {
        LongStream.range(startingNum, startingNum + consecutiveNums)
                .forEach(this::printPropertyList);
        System.out.println();
    }

    private void printSingleNumProperties(long num) {
        System.out.printf("Properties of %,d\n", num);
        System.out.printf("%14s%b\n", "buzz: ", BuzzNumber.isBuzzNumber.test(num));
        System.out.printf("%14s%b\n", "duck: ", DuckNumber.isDuckNumber.test(num));
        System.out.printf("%14s%b\n", "palindromic: ", PalindromicNumber.isPalindromic.test(num));
        System.out.printf("%14s%b\n", "gapful: ", GapfulNumber.isGapfulNumber.test(num));
        System.out.printf("%14s%b\n", "spy: ", SpyNumber.isSpyNumber.test(num));
        System.out.printf("%14s%b\n", "even: ", isEven.test(num));
        System.out.printf("%14s%b\n", "odd: ", isOdd.test(num));
        System.out.println();
    }

    private List<String> getRequestFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a request: ");
        String request = scanner.nextLine();
        System.out.println();
        return List.of(request.split("\\s+"));
    }

    private void printWelcomeMessage() {
        System.out.println("Welcome to Amazing Numbers!\n");
    }

    private void printInstructions() {
        System.out.println("Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "  * the first parameter represents a starting number;\n" +
                "  * the second parameter shows how many consecutive numbers are to be processed;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n");
    }
}
