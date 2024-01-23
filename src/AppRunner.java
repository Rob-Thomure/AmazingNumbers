import amazingNumberHelpers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class AppRunner {
    public static final Predicate<Long> isEven = num -> num % 2 == 0;
    public static final Predicate<Long> isNotEven = num -> isEven.negate().test(num);
    public static final Predicate<Long> isOdd = num -> num % 2 != 0;
    public static final Predicate<Long> isNotOdd = num -> isOdd.negate().test(num);

    public void runApp() {
        boolean exit = false;
        printWelcomeMessage();
        printInstructions();
        while (!exit) {
            List<String> userRequest = getRequestFromUser();

            InputValidator inputValidator = new InputValidator(userRequest);
            if (inputValidator.isValid()) {
                if (userRequest.get(0).equals("0")) {
                    System.out.println("Goodbye!");
                    exit = true;
                } else if (userRequest.size() == 1) {
                    executeOneParameter(userRequest);
                } else if (userRequest.size() == 2) {
                    executeTwoParameters(userRequest);
                } else if (userRequest.size() == 3) {
                    executeThreeParameters(userRequest);
                } else {
                    executeFourParameter(userRequest);
                }
            }


        }
    }

    public void executeOneParameter(List<String> parameters) {
        int num = Integer.parseInt(parameters.get(0));
        AmazingNumber amazingNumber = new AmazingNumber(num);
        printSingleNumProperties(amazingNumber);
    }

    public void executeTwoParameters(List<String> parameters) {
        printMultiNumProperties(Long.parseLong(parameters.get(0)), Long.parseLong(parameters.get(1)));
    }

    public void executeThreeParameters(List<String> parameters) {
        Predicate<Long> filter = getFilter(parameters.get(2));
        printFilteredPropertyList(Long.parseLong(parameters.get(0)), Long.parseLong(parameters.get(1)), filter);
    }

    public void executeFourParameter(List<String> parameters) {
        List<String> properties = parameters.stream()
                .skip(2)
                .toList();

        Predicate<Long> compositePredicate =  composePredicates(properties);
        printMultiFilteredPropertyList(Long.parseLong(parameters.get(0)), Long.parseLong(parameters.get(1)),
                compositePredicate);
    }

    public Predicate<Long> composePredicates(List<String> parameters) {
         return parameters.stream()
                .map(this::getFilter)
                .reduce(pr -> true, Predicate::and);
    }

    private void printMultiFilteredPropertyList(Long num, Long occurrences, Predicate<Long> compositeFilter) {
        LongStream.iterate(num, i -> i + 1)
                .boxed()
                .filter(compositeFilter)
                .limit(occurrences)
                .forEach(this::printPropertyList);
        System.out.println();
    }

    public void printFilteredPropertyList(long num, long occurrences, Predicate<Long> filter) {
        LongStream.iterate(num, i -> i + 1)
                .boxed()
                .filter(filter)
                .limit(occurrences)
                .forEach(this::printPropertyList);
        System.out.println();




    }

    private Predicate<Long> getFilter(String filter) {
        return switch (filter.toLowerCase()) {
            case "buzz" -> BuzzNumber.isBuzzNumber;
            case "-buzz" -> BuzzNumber.isNotBuzzNumber;
            case "duck" -> DuckNumber.isDuckNumber;
            case "-duck" -> DuckNumber.isNotDuckNumber;
            case "palindromic" -> PalindromicNumber.isPalindromic;
            case "-palindromic" -> PalindromicNumber.isNotPalindromic;
            case "gapful" -> GapfulNumber.isGapfulNumber;
            case "-gapful" -> GapfulNumber.isNotGapfulNumber;
            case "spy" -> SpyNumber.isSpyNumber;
            case "-spy" -> SpyNumber.isNotSpyNumber;
            case "square" -> PerfectSquareNumber.isPerfectSquare;
            case "-square" -> PerfectSquareNumber.isNotPerfectSquare;
            case "sunny" -> SunnyNumber.isSunnyNumber;
            case "-sunny" -> SunnyNumber.isNotSunnyNumber;
            case "jumping" -> JumpingNumber.isJumpingNumber;
            case "-jumping" -> JumpingNumber.isNotJumpingNumber;
            case "happy" -> HappyNumber.isHappyNumber;
            case "-happy" -> HappyNumber.isNotHappyNumber;
            case "sad" -> SadNumber.isSadNumber;
            case "-sad" -> SadNumber.isNotSadNumber;
            case "even" -> isEven;
            case "-even" -> isNotEven;
            case "odd" -> isOdd;
            case "-odd" -> isNotOdd;

            default -> null;
        };
    }



    private void printPropertyList(Long num) {
        List<String> list = new ArrayList<>();
        if (isEven.test(num)) list.add("even");
        if (isOdd.test(num)) list.add("odd");
        if (BuzzNumber.isBuzzNumber.test(num)) list.add("buzz");
        if (DuckNumber.isDuckNumber.test(num)) list.add("duck");
        if (PalindromicNumber.isPalindromic.test(num)) list.add("palindromic");
        if (GapfulNumber.isGapfulNumber.test(num)) list.add("gapful");
        if (SpyNumber.isSpyNumber.test(num)) list.add("spy");
        if (PerfectSquareNumber.isPerfectSquare.test(num)) list.add("square");
        if (SunnyNumber.isSunnyNumber.test(num)) list.add("sunny");
        if (JumpingNumber.isJumpingNumber.test(num)) list.add("jumping");
        if (HappyNumber.isHappyNumber(num)) list.add("happy");
        if (SadNumber.isSadNumber(num)) list.add("sad");
        System.out.printf("   %,d is %s\n", num, String.join(", ", list));
    }

    private void printPropertyList(AmazingNumber amazingNumber) {
        Map<String, Boolean> properties = amazingNumber.getProperties();
        String propertyString = properties.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == true)
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(", "));
        System.out.printf("   %,d is %s\n", amazingNumber.getNum(), propertyString);
    }

    private void printMultiNumProperties(long startingNum, long consecutiveNums) {
        IntStream.range((int) startingNum, (int) (startingNum + consecutiveNums))
                .mapToObj(AmazingNumber::new)
                .forEach(this::printPropertyList);
        System.out.println();
    }

    private void printSingleNumProperties(AmazingNumber amazingNumber) {
        Map<String, Boolean> properties = amazingNumber.getProperties();
        int num = amazingNumber.getNum();
        System.out.printf("Properties of %,d\n", num);
        properties.forEach((key, value) -> System.out.printf("%14s: %b\n", key, value));
        System.out.println();
    }

    private List<String> getRequestFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a request: ");
        String request = scanner.nextLine();
        System.out.println();

        if (request.isEmpty()) return List.of();
        return List.of(request.split("\\s+"));
    }

    private void printWelcomeMessage() {
        System.out.println("Welcome to Amazing Numbers!\n");
    }

    private void printInstructions() {
        System.out.println("""
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be printed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
                - separate the parameters with one space;
                - enter 0 to exit.
                """);
    }


}
