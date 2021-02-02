package service.option;


import entity.BankAccount;
import entity.Transaction;
import model.TypeTransaction;
import repository.DatabaseInMemory;
import repository.implement.BankAccountInMemoryRepository;

import java.util.Date;

public class Transfer {
    private Transfer() {
    }

    public static void doTransfer(BankAccount fromBankAccount, BankAccount toBankAccount, int amount) {
        new BankAccountInMemoryRepository().setBalanceOfBankAccount(fromBankAccount, new BankAccountInMemoryRepository().getBalanceOfBankAccount(fromBankAccount) - amount - view.implement.Transfer.FEE_TRANSFER);
        new BankAccountInMemoryRepository().setBalanceOfBankAccount(toBankAccount, new BankAccountInMemoryRepository().getBalanceOfBankAccount(toBankAccount) + amount);
        Transaction transaction = new Transaction(fromBankAccount, toBankAccount,
                -amount - view.implement.Transfer.FEE_TRANSFER, TypeTransaction.TRANSFER.getContentTransaction(), new Date());
        DatabaseInMemory.addTransaction(transaction);
    }
}
