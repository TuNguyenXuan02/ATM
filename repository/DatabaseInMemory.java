package repository;

import entity.BankAccount;
import entity.Card;
import entity.Transaction;
import entity.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class DatabaseInMemory {
    private DatabaseInMemory() {
    }

    private static final List<BankAccount> bankAccountList = new ArrayList<>();
    private static final List<Card> cardList = new ArrayList<>();
    private static final List<UserInfo> userInfoList = new ArrayList<>();
    private static final String PIN_DEFAULT = "123456";
    private static final boolean STATUS_DEFAULT = false;
    private static int[] limitOfATM;
    private static final List<Transaction> allTransaction = new ArrayList<>();

    static {
        BankAccount bankAccount1 = new BankAccount("21510001234", 20_000_000, "152267464");
        BankAccount bankAccount2 = new BankAccount("21510001235", 10_000_000, "152267465");
        BankAccount bankAccount3 = new BankAccount("21510001236", 5_000_000, "152267466");
        bankAccountList.add(bankAccount1);
        bankAccountList.add(bankAccount2);
        bankAccountList.add(bankAccount3);

        Card card1 = new Card("08001234", "21510001234", PIN_DEFAULT, STATUS_DEFAULT);
        Card card2 = new Card("08001235", "21510001235", PIN_DEFAULT, STATUS_DEFAULT);
        Card card3 = new Card("08001236", "21510001236", PIN_DEFAULT, STATUS_DEFAULT);
        Card card4 = new Card("08001237", "21510001236", PIN_DEFAULT, !STATUS_DEFAULT);
        Card card5 = new Card("08001238", "21510001236", PIN_DEFAULT, STATUS_DEFAULT);
        cardList.add(card1);
        cardList.add(card2);
        cardList.add(card3);
        cardList.add(card4);
        cardList.add(card5);

        UserInfo userInfo1 = new UserInfo("152267464", "Stanton Jones");
        UserInfo userInfo2 = new UserInfo("152267465", "Kolby Windler");
        UserInfo userInfo3 = new UserInfo("152267466", "Dudley Considine");
        userInfoList.add(userInfo1);
        userInfoList.add(userInfo2);
        userInfoList.add(userInfo3);


        limitOfATM = new int[]{5, 10, 15, 20, 25, 30};
    }

    public static List<Card> getCardList() {
        return cardList;
    }

    public static List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public static List<Transaction> getAllTransaction() {
        return allTransaction;
    }

    public static void addTransaction(Transaction transaction) {
        allTransaction.add(transaction);
    }

    public static int[] getLimitOfATM() {
        return limitOfATM.clone();
    }

    public static void setLimitOfATM(int[] limitOfATM) {
        DatabaseInMemory.limitOfATM = limitOfATM;
    }

    public static List<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

}
