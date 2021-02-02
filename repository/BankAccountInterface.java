package repository;

import entity.BankAccount;
import entity.Card;

public interface BankAccountInterface {
    BankAccount getBankAccount(String accountNumber);

    int getBalanceOfBankAccount(BankAccount bankAccount);

    void setBalanceOfBankAccount(BankAccount bankAccount, int balance);

    Card getACardReference(String accountNumber);

    String getIdentityCardNumber(BankAccount bankAccount);

    String getAccountNumber(BankAccount bankAccount);
}
