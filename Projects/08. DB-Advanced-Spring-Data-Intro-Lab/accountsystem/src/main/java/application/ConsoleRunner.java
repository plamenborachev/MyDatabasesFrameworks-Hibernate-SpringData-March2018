package application;

import application.models.Account;
import application.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import application.services.account.AccountServiceImpl;
import application.services.user.UserServiceImpl;

import java.math.BigDecimal;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {
    private UserServiceImpl userService;
    private AccountServiceImpl accountService;

    @Autowired
    public ConsoleRunner(UserServiceImpl userService, AccountServiceImpl accountService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @Override
    public void run(String... strings) throws Exception {
        User user = new User();
        user.setUsername("example");
        user.setAge(20);

        Account account = new Account();
        account.setBalance(new BigDecimal(25000));

        user.getAccounts().add(account);
        userService.registerUser(user);

        accountService.withdrawMoney(new BigDecimal(20000), account.getId());
        accountService.transferMoney(new BigDecimal(20000), 1L);
    }
}
