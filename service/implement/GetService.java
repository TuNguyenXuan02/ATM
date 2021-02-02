package service.implement;

import entity.BankAccount;
import entity.Card;
import entity.UserInfo;
import repository.implement.BankAccountInMemoryRepository;
import repository.implement.CardInMemoryRepository;
import repository.implement.UserInfoInMemoryRepository;
import service.Get;

public class GetService implements Get {
    @Override
    public String getFullNameWithCard(Card cardTarget) {
        BankAccountInMemoryRepository bankAccountInMemoryRepository = new BankAccountInMemoryRepository();
        UserInfoInMemoryRepository userInfoInMemoryRepository = new UserInfoInMemoryRepository();

        BankAccount bankAccountTarget = bankAccountInMemoryRepository.getBankAccount(cardTarget.getAccountNumber());
        UserInfo userInfoTarget = userInfoInMemoryRepository.getUserInfo(bankAccountTarget.getIdentityCardNumber());

        return userInfoInMemoryRepository.getFullName(userInfoTarget);
    }

    @Override
    public BankAccount getBankAccountWithCard(Card card) {
        String accountNumber = new CardInMemoryRepository().getAccountNumber(card);
        return new BankAccountInMemoryRepository().getBankAccount(accountNumber);
    }

    @Override
    public String getFullNameWithBankAccount(BankAccount bankAccount) {
        String id = new BankAccountInMemoryRepository().getIdentityCardNumber(bankAccount);
        return new UserInfoInMemoryRepository().getFullName(new UserInfoInMemoryRepository().getUserInfo(id));
    }
}
