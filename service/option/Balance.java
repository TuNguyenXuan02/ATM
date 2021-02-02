package service.option;

import entity.BankAccount;
import entity.Card;
import repository.implement.BankAccountInMemoryRepository;

public class Balance {

    private Balance() {
    }

    static BankAccountInMemoryRepository bankAccountInMemoryRepository = new BankAccountInMemoryRepository();

    public static int getBalance(Card card) {
        BankAccount bankAccount = bankAccountInMemoryRepository.getBankAccount(card.getAccountNumber());
        return new BankAccountInMemoryRepository().getBalanceOfBankAccount(bankAccount);
    }

    public static void setBalance(Card card, int balance) {
        BankAccount bankAccount = bankAccountInMemoryRepository.getBankAccount(card.getAccountNumber());
        bankAccountInMemoryRepository.setBalanceOfBankAccount(bankAccount, balance);
    }
}
