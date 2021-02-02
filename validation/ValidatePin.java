package validation;

import entity.Card;
import repository.implement.CardInMemoryRepository;
import view.Output;

public class ValidatePin {
    private ValidatePin() {
    }

    static int count = 0;

    public static boolean enterPinAndValidation(String pinTarget, Card card) {
        String cardPin = FormatCardPin.enterPin("Enter card pin: ");
        if (cardPin.equals(pinTarget)) {
            return true;
        } else {
            count++;
            Output.notifyWithPrintln("Pin incorrect! You entered incorrectly: " + count + "/3 times");
            if (count == 3) {
                new CardInMemoryRepository().setBlocked(card, true);
                Output.notifyWithPrintln("You did enter greater than 3 times! Your account has locked.");
                Output.notifyWithPrintln(Output.ROW);
                count = 0;
                return false;
            }
            return enterPinAndValidation(pinTarget, card);
        }
    }
}
