package validation;

import view.Output;

public class FormatCardPin {
    private FormatCardPin() {
    }

    /**
     * get a pin include 6 digits
     *
     * @return String
     */
    public static String enterPin(String message) {
        String pin = Input.promptNextLine(message);
        if (!checkLength(pin)) {
            return enterPin(message);
        } else {
            if (!Helper.checkIsDigits(pin, "Pin not include character. ")) {
                return enterPin(message);
            } else {
                return pin;
            }
        }
    }

    public static boolean checkLength(String pin) {
        if (pin.length() != 6) {
            Output.notifyWithPrintln("Pin must include 6 digits! Please try again. ");
            return false;
        }
        return true;
    }


}

