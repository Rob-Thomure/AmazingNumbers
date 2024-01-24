import java.util.List;
import java.util.Scanner;

public class AppRunner {

    private boolean exit = false;

    public void runApp() {
        printWelcomeMessage();
        printInstructions();
        while (!exit) {
            List<String> userRequest = getRequestFromUser();
            InputValidator inputValidator = new InputValidator(userRequest);
            if (inputValidator.isValid()) {
                switch (userRequest.size()) {
                    case 1 -> executeOneParameter(userRequest);
                    case 2 -> executeTwoParameters(userRequest);
                    default -> executeThreeParameters(userRequest);
                }
            }
        }
    }

    public void executeOneParameter(List<String> parameters) {
        if (parameters.get(0).equals("0"))
            exit();
        else {
            AmazingNumbers amazingNumbers = new AmazingNumbers(Integer.parseInt(parameters.get(0)));
            amazingNumbers.printAmazingNumbers();
        }
    }

    private void exit() {
        System.out.println("Goodbye!");
        exit = true;
    }

    private void executeTwoParameters(List<String> parameters) {
        int num = Integer.parseInt(parameters.get(0));
        int quantityOfAmazingNumbers = Integer.parseInt(parameters.get(1));
        AmazingNumbers amazingNumbers = new AmazingNumbers(num, quantityOfAmazingNumbers);
        amazingNumbers.printAmazingNumbers();
    }

    private void executeThreeParameters(List<String> parameters) {
        int num = Integer.parseInt(parameters.get(0));
        int quantityOfAmazingNumbers = Integer.parseInt(parameters.get(1));
        List<String> parametersList = parameters.subList(2, parameters.size());
        AmazingNumbers amazingNumbers = new AmazingNumbers(num, quantityOfAmazingNumbers, parametersList);
        amazingNumbers.printAmazingNumbers();
    }


    private List<String> getRequestFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a request: ");
        String request = scanner.nextLine();
        System.out.println();
        if (request.isEmpty())
            return List.of();
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
