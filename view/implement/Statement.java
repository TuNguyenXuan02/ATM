package view.implement;

import entity.Card;
import entity.Transaction;
import repository.implement.BankAccountInMemoryRepository;
import repository.implement.CardInMemoryRepository;
import service.implement.GetService;
import view.ExecuteOption;
import view.Format;
import view.Output;

import java.text.SimpleDateFormat;
import java.util.List;

public class Statement implements ExecuteOption {
    @Override
    public void execute(Card card) {
        Output.notifyWithPrintln("PRINT STATEMENT");
        List<Transaction> transactionList = service.option.Statement.getStatement(card);
        //print title
        printFormatString("STT", 4);
        printFormatString("content", 20);
        printFormatString("from", 20);
        printFormatString("beneficiary", 20);
        printFormatString("amount", 20);
        printFormatString("time\n", 25);

        //print content
        int index = 0;
        for (Transaction transaction : transactionList) {
            index++;
            printFormatString(Format.formatNumber(index), 4);
            printFormatString(transaction.getTypeTransaction(), 20);
            printFormatString(new GetService().getFullNameWithBankAccount(transaction.getFromBankAccount()), 20);
            printFormatString(new GetService().getFullNameWithBankAccount(transaction.getToBankAccount()), 20);
            if (transaction.getFromBankAccount().equals(new GetService().getBankAccountWithCard(card))) {
                // money out
                printFormatString(Format.formatNumber(transaction.getAmount()), 20);
            } else {
                // money in
                printFormatString("+" + Format.formatNumber(-transaction.getAmount() - Transfer.FEE_TRANSFER), 20);
            }
            String timestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss\n").format(transaction.getTimestamp());
            printFormatString(timestamp, 25);

        }


    }

    public void printFormatString(String str, int value) {
        Output.notifyWithPrint(Format.formatString(str, value));
    }

}
