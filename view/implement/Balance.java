package view.implement;

import entity.Card;
import service.implement.GetService;
import view.ExecuteOption;
import view.Format;
import view.Output;

public class Balance implements ExecuteOption {
    @Override
    public void execute(Card card) {
        Output.notifyWithPrintln("CHECK BALANCE");

        String fullName = new GetService().getFullNameWithCard(card);
        int balance = service.option.Balance.getBalance(card);

        Output.notifyWithPrintln("Hello: " + fullName);
        Output.notifyWithPrintln("Current balance is: " + Format.formatNumber(balance) + " VND");
    }
}
