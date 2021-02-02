package service.option;

import entity.Card;
import repository.DatabaseInMemory;
import service.implement.GetService;

import java.util.ArrayList;
import java.util.List;

public class Statement {
    private Statement() {
    }

    public static List<entity.Transaction> getStatement(Card card) {
        List<entity.Transaction> result = new ArrayList<>();
        for (entity.Transaction transaction : DatabaseInMemory.getAllTransaction()) {
            if (transaction.getFromBankAccount().equals(new GetService().getBankAccountWithCard(card))
                    || transaction.getToBankAccount().equals(new GetService().getBankAccountWithCard(card))) {
                result.add(transaction);
            }
        }


        return result;
    }
}
