package service;

import entity.BankAccount;
import entity.Card;

public interface Get {
    String getFullNameWithCard(Card card);

    BankAccount getBankAccountWithCard(Card card);

    String getFullNameWithBankAccount(BankAccount bankAccount);
}
