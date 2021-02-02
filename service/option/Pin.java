package service.option;

import entity.Card;
import repository.implement.CardInMemoryRepository;

public class Pin {
    private Pin() {
    }

    public static void changePin(Card card, String newPin) {
        new CardInMemoryRepository().changePin(card, newPin);
    }

    public static boolean checkPin(String oldPin, Card card) {
        return oldPin.equals(new CardInMemoryRepository().getPin(card));
    }

    public static boolean compareNewPinAndNewPinAgain(String newPin, String newPinAgain) {
        return newPin.equals(newPinAgain);
    }
}
