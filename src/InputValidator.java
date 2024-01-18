import java.util.List;
import java.util.function.Predicate;

public class InputValidator {
    List<String> userRequest;

    public InputValidator(List<String> userRequest) {
        this.userRequest = userRequest;
    }

    public boolean isValid() {
        if (userRequest.isEmpty()) {
            return false;
        }
        if (userRequest.size() >= 1) {
            String param = userRequest.get(0);
            if (param.equals("0") || (isNumber(param) && isNaturalNumber(param))) {
                return true;
            }
            System.out.println("The first parameter should be a natural number or zero.\n");
        }
        if (userRequest.size() >= 2) {
            String param = userRequest.get(1);
            if (isNumber(param) && isNaturalNumber(param)) {
                return true;
            }
            System.out.println("The second parameter should be a natural number.\n");
        }
        if (userRequest.size() >= 3) {
            String param = userRequest.get(2).toUpperCase();
            if (ValidProperties.contains(param)) {
                return true;
            }
            System.out.println("Invalid param");
        }
        if (userRequest.size() >= 4) {
            for (int i = 3; i < userRequest.size(); i++) {
                String param = userRequest.get(i);
                if (!ValidProperties.contains(param)) {
                    return false;
                }
            }
            List<String> paramsList = userRequest.subList(2, userRequest.size());
            return !MutuallyExclusiveProperties.isMutuallyExclusiveProperties(paramsList);
        }

        return false;
    }



    private boolean isNumber(String numString) {
        return numString.matches("\\d+");
    }

    private boolean isNaturalNumber(String numString) {
        int num = Integer.parseInt(numString);
        return num > 0;
    }

    public static void main(String[] args) {
        InputValidator inputValidator = new InputValidator(List.of("1"));
        System.out.println(inputValidator.isValid());
    }



}
