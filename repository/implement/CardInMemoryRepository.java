package repository.implement;

import entity.Card;
import repository.CardInterface;
import repository.DatabaseInMemory;

public class CardInMemoryRepository implements CardInterface {
    @Override
    public Card getCard(String cardNumber) {
        for (Card card : DatabaseInMemory.getCardList()) {
            if (card.getCardNumber().equals(cardNumber)) {
                return card;
            }
        }
        return null;
    }

    @Override
    public String getAccountNumber(Card card) {
        return card.getAccountNumber();
    }

    @Override
    public void changePin(Card card, String newPin) {
        card.setPin(newPin);
    }

    @Override
    public String getPin(Card card) {
        return card.getPin();
    }

    @Override
    public boolean isBlocked(Card card) {
        return card.isLocked();
    }

    @Override
    public void setBlocked(Card card, boolean status) {
        card.setLocked(status);
    }

    @Override
    public String getCardNumber(Card card) {
        return card.getCardNumber();
    }
}
