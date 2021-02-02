package validation;

import repository.implement.CardInMemoryRepository;
import view.Output;

public class FormatCardNumber {
    private FormatCardNumber() {
    }

    /**
     * get a card number existed!
     *
     * @return String
     */
    public static String enterCardNumber(String message) {
        String cardNumber = Input.promptNextLine(message);
        String mess = checkFormat(cardNumber);

        //check format
        if (!mess.equals("")) {
            Output.notifyWithPrint(mess);
            if (!Helper.checkIsDigits(cardNumber, "")) {
                Output.notifyWithPrint("Card number not include character! ");
            }
            return enterCardNumber(message);
        } else {
            //check exists
            if (new CardInMemoryRepository().getCard(cardNumber) == null) {
                Output.notifyWithPrint("Card not exists! ");
                return enterCardNumber(message);
            } else {
                return cardNumber;
            }
        }
    }

    public static String checkFormat(String cardNumber) {
        if (cardNumber.length() != 8) {
            return "Card number must include 8 digits! ";
        } else {
            if (!cardNumber.startsWith("0800")) {
                return "Format card is 0800****. ";
            } else {
                return "";
            }
        }
    }

}
