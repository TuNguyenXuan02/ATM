package entity;

import java.util.Date;

public class Transaction {
    private final BankAccount fromBankAccount;
    private final BankAccount toBankAccount;
    private final int amount;
    private final String typeTransaction;
    private final Date timestamp;

    public Transaction(BankAccount fromBankAccount, BankAccount toBankAccount, int amount, String typeTransaction, Date timestamp) {
        this.fromBankAccount = fromBankAccount;
        this.toBankAccount = toBankAccount;
        this.amount = amount;
        this.typeTransaction = typeTransaction;
        this.timestamp = timestamp;
    }

    public BankAccount getFromBankAccount() {
        return fromBankAccount;
    }

    public BankAccount getToBankAccount() {
        return toBankAccount;
    }

    public int getAmount() {
        return amount;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
