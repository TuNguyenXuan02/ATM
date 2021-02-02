package service;

import entity.Card;
import model.Menu;
import service.implement.GetService;
import validation.Input;
import view.ExecuteOption;
import view.Output;
import view.implement.*;

public class Option {
    public void startService(Card card) {
        Output.notifyWithPrintln("Hi " + new GetService().getFullNameWithCard(card) + "...");
        Menu.printMenu();
        executeOption(enterOption(), card);
    }

    public int enterOption() {
        return Input.promptNextInt("Enter your option: ", "Option invalid! ");
    }

    public void doOption(ExecuteOption executeOption, Card card) {
        Output.notifyWithPrintln(Output.ROW);
        executeOption.execute(card);
        Output.notifyWithPrintln(Output.ROW);
        startService(card);
    }

    public void executeOption(int option, Card card) {
        switch (option) {
            case 0:
                doOption(new DisplayDB(), card);
                break;
            case 1:
                doOption(new Pin(), card);
                break;
            case 2:
                doOption(new Balance(), card);
                break;
            case 3:
                doOption(new Withdrawal(), card);
                break;
            case 4:
                doOption(new Transfer(), card);
                break;
            case 5:
                doOption(new Statement(), card);
                break;
            case 6:
                Output.notifyWithPrintln(Output.ROW);
                new Logout().logOut();
                break;
            default:
                Output.notifyWithPrintln("Please enter number from 1 to 6! ");
                startService(card);
        }
    }
}
