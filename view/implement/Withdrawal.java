package view.implement;

import entity.Card;
import model.Denominations;
import service.Option;
import service.option.Balance;
import validation.Input;
import view.ExecuteOption;
import view.Format;
import view.Output;

public class Withdrawal implements ExecuteOption {
    @Override
    public void execute(Card Card) {
        Output.notifyWithPrintln("WITHDRAWAL");
        Output.notifyWithPrintln("The balance after withdrawal must be larger 50 000");
        Output.notifyWithPrintln("Fee withdrawal: 1 100 VND");
        long balance = Balance.getBalance(Card);
        Output.notifyWithPrintln("Current balance: " + Format.formatNumber(balance) + " VND");

        int theAmount = getMoneyWantWithdrawal(Card);

        if (checkEnoughBalance(Card, theAmount)) {
            int[] target = divideAmountWithdrawal(Card, theAmount);
            if (target != null) {
                show(Card, target);
            } else {
                Output.notifyWithPrintln("ATM not enough money.");
                Output.notifyWithPrintln(Output.ROW);
                execute(Card);
            }
        } else {
            Output.notifyWithPrintln("Not enough balance. After withdrawal, the balance must be larger 50 000( fee: 1 1000 VND)");
            Output.notifyWithPrintln(Output.ROW);
            execute(Card);
        }

    }

    public boolean checkEnoughBalance(Card card, int theAmount) {
        return Balance.getBalance(card) - theAmount > 51_100;
    }

    public int[] divideAmountWithdrawal(Card Card, int theAmount) {
        return service.option.Withdrawal.divideDenominations(Card, theAmount);
    }

    public void show(Card Card, int[] result) {
        int count = 0;
        for (int denominations : Denominations.DENOMINATIONS) {
            if (result[count] != 0) {
                String money = Format.formatString(Format.formatNumber(denominations), 9);
                Output.notifyWithPrintln(money + ": " + result[count]);
            }
            count++;
        }
        Output.notifyWithPrintln("Fee: 1,100 VND");
        Output.notifyWithPrintln("Current balance: " + Format.formatNumber(Balance.getBalance(Card)) + " VND");
    }

    public int getMoneyWantWithdrawal(Card card) {
        Output.notifyWithPrintln("1. 3,000,000 VND");
        Output.notifyWithPrintln("2. 1,500,000 VND");
        Output.notifyWithPrintln("3. 1,000,000 VND");
        Output.notifyWithPrintln("4.   500,000 VND");
        Output.notifyWithPrintln("5.   200,000 VND");
        Output.notifyWithPrintln("6. Others");
        Output.notifyWithPrintln("7. Exit");
        int choose = Input.promptNextInt("Choose option: ", "Please enter number from 1 to 7. ");
        switch (choose) {
            case 1:
                return 3_000_000;
            case 2:
                return 1_500_000;
            case 3:
                return 1_000_000;
            case 4:
                return 500_000;
            case 5:
                return 200_000;
            case 6:
                return enterMoney();
            case 7:
                Output.notifyWithPrintln(Output.ROW);
                new Option().startService(card);
                break;
            default:
                Output.notifyWithPrintln("Please enter number from 1 to 7.");
                return getMoneyWantWithdrawal(card);
        }
        return -1;
    }

    public int enterMoney() {
        int result = Input.promptNextInt("Enter the amount: ", " Error ");
        if (result < 50_000) {
            Output.notifyWithPrintln("The amount must be larger 50 000 VND");
            return enterMoney();
        } else {
            if (result % 50_000 != 0) {
                Output.notifyWithPrintln("The amount must be in multiples of 50000 VND");
                return enterMoney();
            } else {
                return result;
            }
        }
    }


}
