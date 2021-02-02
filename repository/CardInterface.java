package repository;

import entity.Card;

public interface CardInterface {
    Card getCard(String cardNumber);

    String getAccountNumber(Card card);

    void changePin(Card card, String newPin);

    String getPin(Card card);

    boolean isBlocked(Card card);

    void setBlocked(Card card, boolean status);

    String getCardNumber(Card card);
}
