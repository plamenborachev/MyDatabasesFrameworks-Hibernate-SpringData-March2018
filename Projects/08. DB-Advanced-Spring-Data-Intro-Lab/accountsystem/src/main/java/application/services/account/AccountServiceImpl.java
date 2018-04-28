package application.services.account;

import application.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import application.repositories.AccountRepository;

import java.math.BigDecimal;

@Service
@Primary
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        if (!this.accountRepository.exists(id)){
            throw new IllegalArgumentException("There is no account with the provided Id!");
        }
        Account acc = accountRepository.findOne(id);
        if (acc.getUser() == null){
            throw new RuntimeException("No user present for this account!");
        }
        if (acc.getBalance().compareTo(money) < 0){
            throw new IllegalArgumentException("There is not enough money in the account!");
        }
        acc.setBalance(acc.getBalance().subtract(money));
        accountRepository.save(acc);
    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {
        Account acc = accountRepository.findOne(id);
        if (acc == null){
            throw new IllegalArgumentException("There is no account with the provided Id!");
        }
        if (acc.getUser() == null){
            throw new RuntimeException("No user present for this account!");
        }
        if(money.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Money can not be negative!");
        }
        acc.setBalance(acc.getBalance().add(money));
        accountRepository.save(acc);
    }
}
