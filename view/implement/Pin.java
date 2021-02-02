package view.implement;

import entity.Card;
import service.Option;
import validation.FormatCardPin;
import validation.Helper;
import view.ExecuteOption;
import view.Output;

public class Pin implements ExecuteOption {
    @Override
    public void execute(Card card) {
        Output.notifyWithPrintln("CHANGE PIN");
        changePin(card);
    }

    public void changePin(Card card) {
        String oldPin = enterOldPin(card);
        String newPin = enterNewPin(card);

        if (oldPin.equals(newPin)) {
            Output.notifyWithPrintln("Old pin like new pin. Please try again!");
            if (Helper.questionToExit()) {
                Output.notifyWithPrintln(Output.ROW);
                new Option().startService(card);
            } else {
                execute(card);
            }
        } else {
            service.option.Pin.changePin(card, newPin);
            Output.notifyWithPrintln("Change pin success!");
        }
    }


    public String enterOldPin(Card card) {
        String oldPin;
        for (int index = 1; index <= 3; index++) {
            oldPin = FormatCardPin.enterPin("Enter old pin: ");
            if (service.option.Pin.checkPin(oldPin, card)) {
                return oldPin;
            } else {
                Output.notifyWithPrintln("Pin incorrect! You has enter incorrect " + index + "/3 time");
                if (index == 3) {
                    if (Helper.questionToExit()) {
                        Output.notifyWithPrintln(Output.ROW);
                        new Option().startService(card);
                    } else {
                        return enterOldPin(card);
                    }
                }
            }
        }
        return enterOldPin(card);
    }

    private int count = 0;

    public String enterNewPin(Card card) {
        String newPin = FormatCardPin.enterPin("Enter new pin: ");
        String newPinAgain = FormatCardPin.enterPin("Enter new pin again: ");
        if (service.option.Pin.compareNewPinAndNewPinAgain(newPin, newPinAgain)) {
            return newPin;
        } else {
            count++;
            Output.notifyWithPrintln("New pin not like new pin again. You has enter incorrect " + count + "/3 times");
            if (count == 3) {
                count = 0;
                boolean quest = Helper.questionToExit();
                if (quest) {
                    Output.notifyWithPrintln(Output.ROW);
                    new Option().startService(card);
                } else {
                    enterNewPin(card);
                }
            }
            return enterNewPin(card);
        }
    }
}
