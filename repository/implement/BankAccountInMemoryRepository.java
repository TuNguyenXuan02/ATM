package repository.implement;

import entity.BankAccount;
import entity.Card;
import repository.BankAccountInterface;
import repository.DatabaseInMemory;

public class BankAccountInMemoryRepository implements BankAccountInterface {
    @Override
    public BankAccount getBankAccount(String accountNumber) {
        for (BankAccount bankAccount : DatabaseInMemory.getBankAccountList()) {
            if (bankAccount.getAccountNumber().equals(accountNumber)) {
                return bankAccount;
            }
        }
        return null;
    }

    @Override
    public int getBalanceOfBankAccount(BankAccount bankAccount) {
        return bankAccount.getBalance();
    }

    @Override
    public void setBalanceOfBankAccount(BankAccount bankAccount, int balance) {
        bankAccount.setBalance(balance);
    }

    @Override
    public Card getACardReference(String accountNumber) {
        for (Card card : DatabaseInMemory.getCardList()) {
            if (card.getAccountNumber().equals(accountNumber)) {
                return card;
            }
        }
        return null;
    }

    @Override
    public String getIdentityCardNumber(BankAccount bankAccount) {
        return bankAccount.getIdentityCardNumber();
    }

    @Override
    public String getAccountNumber(BankAccount bankAccount) {
        return bankAccount.getAccountNumber();
    }
}
