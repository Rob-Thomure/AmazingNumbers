import java.util.List;
import java.util.function.Predicate;

public class InputValidator {
    List<String> userRequest;

    public InputValidator(List<String> userRequest) {
        this.userRequest = userRequest;
    }

    public boolean isValid() {
        return switch (userRequest.size()) {
            case 0 -> false;
            case 1 -> firstParamIsValid();
            case 2 -> firstParamIsValid() && secondParamIsValid();
            case 3 -> firstParamIsValid() && secondParamIsValid() && isValidParameterNames();
            default -> firstParamIsValid() && secondParamIsValid() && isValidParameterNames()
                    && isMutuallyExclusiveProperties();
        };
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
