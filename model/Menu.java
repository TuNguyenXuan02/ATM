package model;


import view.Output;

public enum Menu {

    ADMIN("Show database"),
    CHANGE_PIN("Change pin"),
    CHECK_BALANCE("Check balance"),
    WITHDRAWAL("Withdrawal"),
    TRANSFER("Transfer"),
    PRINT("Print statement"),
    QUIT("Quit");
    private final String str;

    Menu(String str) {
        this.str = str;
    }

    public String getString() {
        return this.str;
    }

    public static void printMenu() {
        for (Menu temp : Menu.values()) {
            Output.notifyWithPrintln(temp.ordinal() + ". " + temp.getString());
        }
    }

    @Override
    public String toString() {
        return "(" + this.ordinal() + str;
    }
}
