import java.util.Scanner;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        int input = enterNumber();
        if (isNaturalNumber(input)) {
            BuzzNumber buzzNumber = new BuzzNumber(input);
            buzzNumber.test();
        } else {
            System.out.println("This number is not natural!");
        }
    }

    public static int enterNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a natural number:");
        return scanner.nextInt();
    }

    public static boolean isNaturalNumber(int num) {
        return num > 0;
    }



}
