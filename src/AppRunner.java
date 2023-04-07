import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class AppRunner {
    private final List<List<String>> mutuallyExclusiveProperties = List.of(
            List.of("EVEN", "ODD")
            , List.of("DUCK", "SPY")
            , List.of("SUNNY", "SQUARE"));
    private final Predicate<String> isNumber = str -> str.matches("\\d+");
    private final Predicate<String> isNaturalNumber = num -> Long.parseLong(num) > 0;
    private final Predicate<Long> isEven = num -> num % 2 == 0;
    private final Predicate<Long> isOdd = num -> isEven.negate().test(num);

    public void runApp() {
        boolean exit = false;
        printWelcomeMessage();
        printInstructions();
        while (!exit) {
            List<String> userRequest = getRequestFromUser();
            if (userRequest.get(0).isEmpty()) {
                printInstructions();
            } else if (userRequest.get(0).equals("0")) {
                System.out.println("Goodbye!");
                exit = true;
            } else if (userRequest.size() == 1) {
                executeOneParameter(userRequest);
            } else if (userRequest.size() == 2) {
                executeTwoParameters(userRequest);
            } else if (userRequest.size() == 3) {
                executeThreeParameters(userRequest);
            } else if (userRequest.size() >= 4) {
                executeFourParameter(userRequest);
            }
        }
    }

    public void executeOneParameter(List<String> parameters) {
        if (isNumber.and(isNaturalNumber).test(parameters.get(0))) {
            printSingleNumProperties(Long.parseLong(parameters.get(0)));
        } else {
            System.out.println("The first parameter should be a natural number or zero.\n");
        }
    }

    public void executeTwoParameters(List<String> parameters) {
        if (isNumber.and(isNaturalNumber).negate().test(parameters.get(0))) {
            System.out.println("The first parameter should be a natural number or zero.\n");
        } else if (isNumber.and(isNaturalNumber).negate().test(parameters.get(1))) {
            System.out.println("The second parameter should be a natural number or zero.\n");
        } else {
            printMultiNumProperties(Long.parseLong(parameters.get(0)), Long.parseLong(parameters.get(1)));
        }
    }

    public void executeThreeParameters(List<String> parameters) {
        if (isNumber.and(isNaturalNumber).negate().test(parameters.get(0))) {
            System.out.println("The first parameter should be a natural number or zero.\n");
        } else if (isNumber.and(isNaturalNumber).negate().test(parameters.get(1))) {
            System.out.println("The second parameter should be a natural number or zero.\n");
        } else {
            Predicate<Long> filter = getFilter(parameters.get(2));
            if (null != filter) {
                printFilteredPropertyList(Long.parseLong(parameters.get(0)), Long.parseLong(parameters.get(1)), filter);
            } else {
                List<String> properties = List.of(parameters.get(2));
                printInvalidProperties(properties);
            }
        }
    }

    public void executeFourParameter(List<String> parameters) {
        List<String> properties = parameters.stream()
                .skip(2)
                .toList();
        if (isNumber.and(isNaturalNumber).negate().test(parameters.get(0))) {
            System.out.println("The first parameter should be a natural number or zero.\n");
        } else if (isNumber.and(isNaturalNumber).negate().test(parameters.get(1))) {
            System.out.println("The second parameter should be a natural number or zero.\n");
        } else if (!isValidProperties(properties)) {
            List<String> invalidProperties = createInvalidPropertyList(properties);
            printInvalidProperties(invalidProperties);
        } else if (isMutuallyExclusiveParameters(properties)) {
            System.out.printf("The request contains mutually exclusive properties: [%s]\n" +
                    "There are no numbers with these properties.\n\n", String.join(", ",
                    getMutuallyExclusiveProperties(properties)));
        } else if (!isMutuallyExclusiveParameters(properties) && isValidProperties(properties)) {
            Predicate<Long> compositePredicate =  composePredicates(properties);
            printMultiFilteredPropertyList(Long.parseLong(parameters.get(0)), Long.parseLong(parameters.get(1)),
                    compositePredicate);
        }
    }

    public boolean isValidProperties(List<String> properties) {
        List<String> validProperties = List.of("EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE",
                "SUNNY", "JUMPING");
        for (var element : properties) {
            if (!validProperties.contains(element.toUpperCase())) {
                return false;
            }
        }
        return true;
    }

    public List<String> getMutuallyExclusiveProperties(List<String> properties) {
        List<String> capitalProperties = properties.stream().map(String::toUpperCase).collect(Collectors.toList());
       return mutuallyExclusiveProperties.stream()
                .filter(element -> capitalProperties.containsAll(element))
               .flatMap(e -> e.stream())
               .collect(Collectors.toList());
    }

    public Predicate<Long> composePredicates(List<String> parameters) {
         return parameters.stream()
                .map(this::getFilter)
                .reduce(pr -> true, Predicate::and);
    }

    private boolean isMutuallyExclusiveParameters(List<String> properties) {
        List<String> capitalProperties = properties.stream().map(str -> str.toUpperCase()).collect(Collectors.toList());
        for (var mutuallyExclusive : mutuallyExclusiveProperties) {
            if (capitalProperties.containsAll(mutuallyExclusive)) {
                return true;
            }
        }
        return false;
    }

    private List<String> createInvalidPropertyList(List<String> properties) {
        List<String> validProperties = List.of("EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE",
                "SUNNY", "JUMPING");
        return properties.stream()
                .filter(str -> !validProperties.contains(str.toUpperCase()))
                .collect(Collectors.toList());
    }

    private void printInvalidProperties(List<String> properties) {
        if (properties.size() == 1) {
            System.out.printf("The property [%s] is wrong.\n" +
                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]\n\n"
                    ,properties.get(0));
        } else if (properties.size() >= 2) {
            System.out.printf("The properties [%s] are wrong.\n" +
                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]\n\n"
                    ,String.join(", ", properties));
        }
    }

    private void printMultiFilteredPropertyList(Long num, Long occurrences, Predicate<Long> compositeFilter) {
        LongStream.iterate(num, i -> i + 1)
                .boxed()
                .filter(compositeFilter)
                .limit(occurrences)
                .forEach(this::printPropertyList);
        System.out.println();
    }

    private void printTwoFilteredPropertyList(Long num, Long occurrences, Predicate<Long> firstFilter
            , Predicate<Long> secondFilter) {
        LongStream.iterate(num, i -> i + 1)
                .boxed()
                .filter(firstFilter)
                .filter(secondFilter)
                .limit(occurrences)
                .forEach(this::printPropertyList);
        System.out.println();

    }

    public void printFilteredPropertyList(Long num, Long occurrences, Predicate<Long> filter) {
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
            case "duck" -> DuckNumber.isDuckNumber;
            case "palindromic" -> PalindromicNumber.isPalindromic;
            case "gapful" -> GapfulNumber.isGapfulNumber;
            case "spy" -> SpyNumber.isSpyNumber;
            case "square" -> PerfectSquareNumber.isPerfectSquare;
            case "sunny" -> SunnyNumber.isSunnyNumber;
            case "jumping" -> JumpingNumber.isJumpingNumber2;
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
        if (PerfectSquareNumber.isPerfectSquare.test(num)) list.add("square");
        if (SunnyNumber.isSunnyNumber.test(num)) list.add("sunny");
        if (JumpingNumber.isJumpingNumber2.test(num)) list.add("jumping");
        if (isEven.test(num)) list.add("even");
        if (isOdd.test(num)) list.add("odd");
        System.out.printf("   %,d is %s\n", num, String.join(", ", list));
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
        System.out.printf("%14s%b\n", "square: ", PerfectSquareNumber.isPerfectSquare.test(num));
        System.out.printf("%14s%b\n", "sunny: ", SunnyNumber.isSunnyNumber.test(num));
        System.out.printf("%14s%b\n", "jumping: ", JumpingNumber.isJumpingNumber2.test(num));
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
                "- two natural numbers and properties to search for;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n");
    }


}
