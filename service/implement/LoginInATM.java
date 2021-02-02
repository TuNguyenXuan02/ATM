package service.implement;

import entity.Card;
import repository.implement.CardInMemoryRepository;
import validation.ValidatePin;
import validation.FormatCardNumber;
import service.LoginWithCardNumberAndPin;
import view.Output;

public class LoginInATM implements LoginWithCardNumberAndPin {
    @Override
    public Card auth() {
        //create instance repository
        CardInMemoryRepository cardInMemoryRepository = new CardInMemoryRepository();

        // validated card exists
        String cardNumber = FormatCardNumber.enterCardNumber("Enter card number: ");//DB
        Card cardTarget = cardInMemoryRepository.getCard(cardNumber);
        //get fullName
        String fullName = new GetService().getFullNameWithCard(new CardInMemoryRepository().getCard(cardNumber));
        Output.notifyWithPrintln("Welcome " + fullName);

        // check lock
        if (!cardInMemoryRepository.isBlocked(cardTarget)) {
            String pinTarget = cardInMemoryRepository.getPin(cardTarget);
            // enter pin and check
            if (ValidatePin.enterPinAndValidation(pinTarget, cardTarget)) {
                return cardTarget;
            } else {
                return auth();
            }
        } else {
            Output.notifyWithPrintln("Your card has been locked! Because you entered incorrectly 3 times");
            Output.notifyWithPrintln(Output.ROW);
            return auth();
        }
    }
}
