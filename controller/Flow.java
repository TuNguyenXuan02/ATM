package controller;

import entity.Card;
import service.Option;
import view.Output;
import service.implement.LoginInATM;

public class Flow {
    private Flow() {
    }

    public static void startFlow() {
        welcome();
        Card card = login();
        Output.notifyWithPrintln("LOGIN SUCCESS!");
        Output.notifyWithPrintln(Output.ROW);
        executeMenuOption(card);
    }

    public static void welcome() {
        Output.notifyWithPrintln("---------------- Welcome you to ATM of Tu Nguyen ----------------");
        Output.notifyWithPrintln("Card number include 8 digits. Format: 0800****. Example: 08001234");
    }

    public static Card login() {
        return new LoginInATM().auth();
    }

    public static void executeMenuOption(Card card) {
        new Option().startService(card);
    }
}
