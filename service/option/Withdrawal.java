package service.option;

import entity.BankAccount;
import entity.Card;
import entity.Transaction;
import model.Denominations;
import model.TypeTransaction;
import repository.DatabaseInMemory;
import repository.implement.BankAccountInMemoryRepository;
import service.implement.GetService;

import java.util.Date;

public class Withdrawal {
    private Withdrawal() {
    }

    public static int[] divideDenominations(Card card, int theAmount) {
        int[] result = new int[6];
        int copyTheAmount = theAmount;
        int[] limitOfATM = DatabaseInMemory.getLimitOfATM();
        int count = 0;
        for (int denominations : Denominations.DENOMINATIONS) {
            int number = theAmount / denominations;
            if (number > limitOfATM[count]) {
                number = limitOfATM[count];
            }
            limitOfATM[count] -= number;
            theAmount -= number * denominations;
            result[count] = number;
            count++;
        }
        if (theAmount > 0) {
            return null;
        } else if (theAmount == 0) {
            withdrawalFromATM(limitOfATM);
            BankAccount bankAccount = new BankAccountInMemoryRepository().getBankAccount(card.getAccountNumber());
            int balance = new BankAccountInMemoryRepository().getBalanceOfBankAccount(bankAccount) - copyTheAmount - 1100;
            Balance.setBalance(card, balance);
            logTransaction(card, -copyTheAmount - 1100);
        }

        return result;
    }

    public static void withdrawalFromATM(int[] limitAfterWithdrawal) {
        DatabaseInMemory.setLimitOfATM(limitAfterWithdrawal);
    }

    public static void logTransaction(Card card, int theAmount) {
        BankAccount bankAccount = new GetService().getBankAccountWithCard(card);
        Transaction transaction = new Transaction(bankAccount, bankAccount, theAmount,
                TypeTransaction.WITHDRAWAL.getContentTransaction(), new Date());
        DatabaseInMemory.addTransaction(transaction);
    }
}
