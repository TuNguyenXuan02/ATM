package service;

import entity.Card;
import repository.implement.CardInMemoryRepository;

public class CheckCard {
    private CheckCard() {
    }

    public static Card getCard(String cardNumber) {
        return new CardInMemoryRepository().getCard(cardNumber);
    }

    public static boolean checkIsBlocked(Card card) {
        return new CardInMemoryRepository().isBlocked(card);
    }
}
