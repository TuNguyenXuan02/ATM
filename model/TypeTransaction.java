package model;

public enum TypeTransaction {
    WITHDRAWAL("WITHDRAWAL AT ATM"),
    TRANSFER("TRANSFER IN BANK");

    private final String s;

    TypeTransaction(String s) {
        this.s = s;
    }

    public String getContentTransaction() {
        return this.s;
    }
}
