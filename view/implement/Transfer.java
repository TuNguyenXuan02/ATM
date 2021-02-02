package view.implement;

import entity.Card;
import repository.implement.BankAccountInMemoryRepository;
import repository.implement.CardInMemoryRepository;
import service.CheckCard;
import service.Option;
import service.implement.GetService;
import service.option.Balance;
import validation.FormatCardNumber;
import validation.Input;
import view.ExecuteOption;
import view.Format;
import view.Output;

public class Transfer implements ExecuteOption {
    public static final int FEE_TRANSFER = 2000;

    @Override
    public void execute(Card fromCard) {
        Output.notifyWithPrintln("TRANSFER");
        int choose = enterChooseTransfer();
        switch (choose) {
            case 1:
                transferWithAccountNumber(fromCard, enterAccountNumberNeedTransfer());
                break;
            case 2:
                transferWithCardNumber(fromCard, enterCardNumberNeedTransfer());
                break;
            case 3:
                Output.notifyWithPrintln(Output.ROW);
                new Option().startService(fromCard);
                break;
            default:
                Output.notifyWithPrintln("Your choose invalid!");
                execute(fromCard);
                break;
        }

    }

    public int enterChooseTransfer() {
        Output.notifyWithPrintln("1. Transfer with account number (Example: 21510001234)");
        Output.notifyWithPrintln("2. Transfer with card number (8 digits and start with 0800 Example: 08001234)");
        Output.notifyWithPrintln("3. Exit");
        int input = Input.promptNextInt("Enter your choose: ", "Your choose invalid! ");
        if (input == 1 || input == 2 || input == 3) {
            return input;
        } else {
            return enterChooseTransfer();
        }
    }

    private void transferWithAccountNumber(Card fromCard, String accountNumberNeedTransfer) {
        String toAccountNumber= new CardInMemoryRepository().getAccountNumber(fromCard);
        try{
            if(toAccountNumber.equals(accountNumberNeedTransfer)){
                Output.notifyWithPrintln("You can't transfer for yourself");
                Output.notifyWithPrintln(Output.ROW);
                execute(fromCard);
            }
            if (toAccountNumber != null) {
                continueTransfer(fromCard, new BankAccountInMemoryRepository().getACardReference(accountNumberNeedTransfer));
            } else {
                Output.notifyWithPrintln("Account not exists");
                execute(fromCard);
            }
        }catch (Exception e) {
            Output.notifyWithPrintln("Bank account not exists!\n");
            execute(fromCard);
        }
    }

    public void transferWithCardNumber(Card fromCard, String cardNumberNeedTransfer) {
        CardInMemoryRepository cardInMemoryRepository= new CardInMemoryRepository();
        try {
            Card toCard = CheckCard.getCard(cardNumberNeedTransfer);
            if (cardInMemoryRepository.getAccountNumber(toCard).equals(cardInMemoryRepository.getAccountNumber(fromCard))) {
                Output.notifyWithPrintln("You can't transfer for yourself");
                Output.notifyWithPrintln(Output.ROW);
                execute(fromCard);
            }

            if (CheckCard.checkIsBlocked(toCard)) {
                Output.notifyWithPrintln("Transfer to: " + new GetService().getFullNameWithCard(toCard));
                Output.notifyWithPrintln("Can't transfer! This account has blocked!\n");
                execute(fromCard);
            }

            if (toCard != null) {
                continueTransfer(fromCard, toCard);
            } else {
                Output.notifyWithPrintln("Account not exists");
                execute(fromCard);
            }
        } catch (Exception e) {
            Output.notifyWithPrintln("Bank account not exists!\n");
            execute(fromCard);
        }

    }

    public String enterCardNumberNeedTransfer() {
        return FormatCardNumber.enterCardNumber("Enter card number: ");
    }


    private void continueTransfer(Card fromCard, Card toCard) {
        Output.notifyWithPrintln("Transfer to: " + new GetService().getFullNameWithCard(toCard));
        Output.notifyWithPrintln("1. Continue");
        Output.notifyWithPrintln("2. Exit");
        int choose = Input.promptNextInt("Enter your choose:  ", "error continueTransfer ");
        switch (choose) {
            case 1:
                enterAmount(fromCard, toCard);
                break;
            case 2:
                execute(fromCard);
                break;
            default:
                continueTransfer(fromCard, toCard);
        }
    }

    public void enterAmount(Card fromCard, Card toCard) {
        int input = Input.promptNextInt("Enter amount need transfer: ", "Please enter number! ");
        if (input <= 0) {
            Output.notifyWithPrintln("Amount transfer must be larger 0");
            enterAmount(fromCard, toCard);
        }

        if (Balance.getBalance(fromCard) - input - FEE_TRANSFER > 0) {
            service.option.Transfer.doTransfer(new GetService().getBankAccountWithCard(fromCard), new GetService().getBankAccountWithCard(toCard), input);
            Output.notifyWithPrintln("");
            Output.notifyWithPrintln("From: " +new GetService().getFullNameWithCard(toCard));
            Output.notifyWithPrintln("To: " + new GetService().getFullNameWithCard(fromCard));
            Output.notifyWithPrintln("Amount transfer: " + Format.formatNumber(input) + " VND");
            Output.notifyWithPrintln("Fee transfer: 2,000 VND");
            Output.notifyWithPrintln("Current balance: " + Format.formatNumber(Balance.getBalance(fromCard)) + " VND");
            Output.notifyWithPrintln("TRANSFER SUCCESS!");
            Output.notifyWithPrintln(Output.ROW);
            new Option().startService(fromCard);
        } else {
            Output.notifyWithPrintln("The balance after transfer must be larger 50,000 VND( fee:" + FEE_TRANSFER + " VND)");
            enterAmount(fromCard, toCard);
        }
    }

    public String enterAccountNumberNeedTransfer() {
        return Input.promptNextLine("Enter account number: ");
    }
}
