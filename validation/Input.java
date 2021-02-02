package validation;

import view.Output;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    private Input() {

    }

    static Scanner scanner = new Scanner(System.in);

    public static int promptNextInt(String message, String errorMessage) {
        Output.notifyWithPrint(message);
        int n;
        try {
            n = scanner.nextInt();
            scanner.nextLine();
            return n;
        } catch (InputMismatchException inputMismatchException) {
            Output.notifyWithPrint(errorMessage);
            scanner.nextLine();
            return promptNextInt(message, errorMessage);
        }
    }


    public static String promptNextLine(String message) {
        Output.notifyWithPrint(message);
        return scanner.nextLine();
    }

}
