package view.implement;

import entity.BankAccount;
import entity.Card;
import entity.Transaction;
import entity.UserInfo;
import model.Denominations;
import repository.DatabaseInMemory;
import repository.implement.BankAccountInMemoryRepository;
import repository.implement.CardInMemoryRepository;
import repository.implement.UserInfoInMemoryRepository;
import service.implement.GetService;
import view.ExecuteOption;
import view.Format;
import view.Output;

import java.text.SimpleDateFormat;
import java.util.List;

public class DisplayDB implements ExecuteOption {

    @Override
    public void execute(Card card) {
        Output.notifyWithPrintln("SHOW DATABASE");
        Output.notifyWithPrintln("\nBank Account");
        showBankAccount();
        Output.notifyWithPrintln("\nCard");
        showCard();
        Output.notifyWithPrintln("\nUser Info");
        showUserInfo();
        Output.notifyWithPrintln("\nATM");
        showATM();
        Output.notifyWithPrintln("\nTransaction");
        showTransaction();
    }

    private void showATM() {
        int[] limitOfATM = DatabaseInMemory.getLimitOfATM();
        printFormatString("denomination", 15);
        printFormatString("amount", 5);
        Output.notifyWithPrint("\n");
        int count = 0;
        int sum = 0;
        for (int denomination : Denominations.DENOMINATIONS) {
            printFormatString(Format.formatNumber(denomination), 15);
            printFormatString(Format.formatNumber(limitOfATM[count]), 5);
            Output.notifyWithPrint("\n");
            sum += denomination * limitOfATM[count];
            count++;
        }
        Output.notifyWithPrintln("Sum: " + Format.formatNumber(sum) + " VND");

    }

    private void showCard() {
        printFormatString("id", 4);
        printFormatString("card number", 14);
        printFormatString("account number", 14);
        printFormatString("pin", 10);
        printFormatString("is blocked", 10);
        Output.notifyWithPrint("\n");
        int index = 0;
        for (Card card : DatabaseInMemory.getCardList()) {
            printFormatString(Format.formatNumber(++index), 4);
            printFormatString(new CardInMemoryRepository().getCardNumber(card), 14);
            printFormatString(new CardInMemoryRepository().getAccountNumber(card), 14);
            printFormatString(new CardInMemoryRepository().getPin(card), 10);
            printFormatString(Boolean.toString(new CardInMemoryRepository().isBlocked(card)), 10);
            Output.notifyWithPrint("\n");
        }

    }

    private void showUserInfo() {
        printFormatString("id", 4);
        printFormatString("identity card number", 20);
        printFormatString("full name", 30);
        Output.notifyWithPrint("\n");
        int index = 0;
        for (UserInfo userInfo : DatabaseInMemory.getUserInfoList()) {
            printFormatString(Format.formatNumber(++index), 4);
            printFormatString(new UserInfoInMemoryRepository().getIdentityCardNumber(userInfo), 20);
            printFormatString(new UserInfoInMemoryRepository().getFullName(userInfo), 30);
            Output.notifyWithPrint("\n");
        }
    }

    public void printFormatString(String str, int value) {
        Output.notifyWithPrint(Format.formatString(str, value));
    }

    public void showBankAccount() {
        printFormatString("id", 4);
        printFormatString("account number", 14);
        printFormatString("balance(VND)", 15);
        printFormatString("identity card number", 20);
        Output.notifyWithPrint("\n");

        //print content
        int index = 0;
        for (BankAccount bankAccount : DatabaseInMemory.getBankAccountList()) {
            printFormatString(Format.formatNumber(++index), 4);
            printFormatString(bankAccount.getAccountNumber(), 14);
            printFormatString(Format.formatNumber(new BankAccountInMemoryRepository().getBalanceOfBankAccount(bankAccount)), 15);
            printFormatString(new BankAccountInMemoryRepository().getIdentityCardNumber(bankAccount), 20);
            Output.notifyWithPrint("\n");
        }
    }

    public void printTitleTransaction() {
        printFormatString("STT", 4);
        printFormatString("from", 20);
        printFormatString("account number( from)", 23);
        printFormatString("to", 20);
        printFormatString("account number( to)", 20);
        printFormatString("amount", 15);
        printFormatString("content", 25);
        printFormatString("time\n", 25);
    }

    public void printATransaction(Transaction transaction, int index) {
        printFormatString(Format.formatNumber(index), 4);
        printFormatString(new GetService().getFullNameWithBankAccount(transaction.getFromBankAccount()), 20);
        printFormatString(new BankAccountInMemoryRepository().getAccountNumber(transaction.getFromBankAccount()), 23);
        printFormatString(new GetService().getFullNameWithBankAccount(transaction.getToBankAccount()), 20);
        printFormatString(new BankAccountInMemoryRepository().getAccountNumber(transaction.getToBankAccount()), 20);
        printFormatString(Format.formatNumber(transaction.getAmount()), 15);
        printFormatString(transaction.getTypeTransaction(), 25);
        String timestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss\n").format(transaction.getTimestamp());
        printFormatString(timestamp, 25);
    }

    public void printContentTransaction(List<Transaction> allTransaction) {
        int index = 0;
        for (Transaction transaction : allTransaction) {
            index++;
            printATransaction(transaction, index);
        }
    }

    public void showTransaction() {
        //show title
        printTitleTransaction();

        //show content
        printContentTransaction(DatabaseInMemory.getAllTransaction());
    }
}
