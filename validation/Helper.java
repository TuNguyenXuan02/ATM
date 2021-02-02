package validation;

import view.Output;

public class Helper {
    private Helper() {

    }

    public static boolean questionToExit() {
        Output.notifyWithPrintln("Do you want exit? Enter Y(Yes) or N(No)!");
        String choose = Input.promptNextLine("Enter your choose: ");
        if (choose.equals("Y")) {
            return true;
        } else if (choose.equals("N")) {
            return false;
        }
        return questionToExit();
    }

    public static boolean checkIsDigits(String str, String mess) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
                Output.notifyWithPrintln(mess);
                return false;
            }
        }
        return true;
    }
}
