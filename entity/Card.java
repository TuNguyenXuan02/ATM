package entity;

public class Card {
    private final String cardNumber;
    private final String accountNumber;
    private String pin;
    private boolean isLocked;

    public Card(String cardNumber, String accountNumber, String pin, boolean isLocked) {
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.isLocked = isLocked;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}
