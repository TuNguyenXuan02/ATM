package entity;

public class BankAccount {

    private final String accountNumber;
    private int balance;
    private final String identityCardNumber;

    public BankAccount(String accountNumber, int balance, String identityCardNumber) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.identityCardNumber = identityCardNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }
}
