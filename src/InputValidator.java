import java.util.List;
import java.util.function.Predicate;

public class InputValidator {
    List<String> userRequest;

    public InputValidator(List<String> userRequest) {
        this.userRequest = userRequest;
    }

    public boolean isValid() {
        return switch (userRequest.size()) {
            case 0 -> printInstructions();
            case 1 -> firstParamIsValid();
            case 2 -> firstParamIsValid() && secondParamIsValid();
            case 3 -> firstParamIsValid() && secondParamIsValid() && isValidParameterNames();
            default -> firstParamIsValid() && secondParamIsValid() && isValidParameterNames()
                    && isMutuallyExclusiveProperties();
        };
    }



    private boolean printInstructions() {
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
        return false;
    }

    private boolean firstParamIsValid() {
        String param = userRequest.get(0);
        if (param.equals("0") || (isNumber(param) && isNaturalNumber(param))) {
            return true;
        }
        System.out.println("The first parameter should be a natural number or zero.\n");
        return false;
    }

    private boolean secondParamIsValid() {
        String param = userRequest.get(1);
        if (isNumber(param) && isNaturalNumber(param)) {
            return true;
        }
        System.out.println("The second parameter should be a natural number.\n");
        return false;
    }

    private boolean isValidParameterNames() {
        for (int i = 2; i < userRequest.size(); i++) {
            String param = userRequest.get(i);
            if (!ValidProperties.contains(param)) {
                System.out.println("InvalidParameter: " + param);
                System.out.println();
                return false;
            }
        }
        return true;
    }

    private boolean isMutuallyExclusiveProperties() {
        List<String> paramsList = userRequest.subList(2, userRequest.size());
        if (MutuallyExclusiveProperties.isMutuallyExclusiveProperties(paramsList)) {
            printMutuallyExclusiveProperties();
        }
        return !MutuallyExclusiveProperties.isMutuallyExclusiveProperties(paramsList);
    }



    private boolean isNumber(String numString) {
        return numString.matches("\\d+");
    }

    private boolean isNaturalNumber(String numString) {
        int num = Integer.parseInt(numString);
        return num > 0;
    }

    private void printMutuallyExclusiveProperties() {
        System.out.printf("The request contains mutually exclusive properties: [%s]\n" +
                "There are no numbers with these properties.\n\n", String.join(", ",
                MutuallyExclusiveProperties.getMatchingMutuallyExclusiveProperties(userRequest)));
    }







}
