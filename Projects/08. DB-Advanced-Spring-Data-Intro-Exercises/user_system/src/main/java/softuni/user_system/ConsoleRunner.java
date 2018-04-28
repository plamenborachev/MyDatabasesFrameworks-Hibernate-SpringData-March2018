package softuni.user_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.user_system.entities.User;
import softuni.user_system.services.interfaces.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private BufferedReader bufferedReader;
    private SimpleDateFormat simpleDateFormat;

    @Autowired
    public ConsoleRunner(UserService userService) {
        this.userService = userService;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        this.simpleDateFormat = new SimpleDateFormat("d MMM yyyy");
    }

    @Override
    public void run(String... strings) throws Exception {
        addUsersToDb();

        System.out.println("Please enter email provider:");
        getUsersByEmailProvider(this.bufferedReader.readLine());

        System.out.println("Please enter date:");
        removeInactiveUsers(this.bufferedReader.readLine());
    }

    private void addUsersToDb() throws ParseException {

        User user1 = new User();
        user1.setUsername("pesho123");
        user1.setPassword("paS12@");
        user1.setEmail("pesho@gmail.com");
        user1.setAge(30);
        Date lastTimeLoggedIn = this.simpleDateFormat.parse("13 Oct 2004");
        user1.setLastTimeLoggedIn(lastTimeLoggedIn);

        User user2 = new User();
        user2.setUsername("vanko1");
        user2.setPassword("paS12@2");
        user2.setEmail("vanko1@gmail.com");
        user2.setAge(40);
        lastTimeLoggedIn = this.simpleDateFormat.parse("10 Oct 2004");
        user2.setLastTimeLoggedIn(lastTimeLoggedIn);

        User user3 = new User();
        user3.setUsername("goshko_n00b");
        user3.setPassword("paS12@3");
        user3.setEmail("gn00b@gmail.com");
        user3.setAge(50);
        lastTimeLoggedIn = this.simpleDateFormat.parse("09 Oct 2004");
        user3.setLastTimeLoggedIn(lastTimeLoggedIn);

        User user4 = new User();
        user4.setUsername("penbo");
        user4.setPassword("paS12@5");
        user4.setEmail("pen@yahoo.co.uk");
        user4.setAge(25);
        lastTimeLoggedIn = this.simpleDateFormat.parse("08 Oct 2004");
        user4.setLastTimeLoggedIn(lastTimeLoggedIn);

        User user5 = new User();
        user5.setUsername("catLady");
        user5.setPassword("paS12@6");
        user5.setEmail("stepheny.p@yahoo.co.uk");
        user5.setAge(35);
        lastTimeLoggedIn = this.simpleDateFormat.parse("07 Oct 2004");
        user5.setLastTimeLoggedIn(lastTimeLoggedIn);

        userService.persist(user1);
        userService.persist(user2);
        userService.persist(user3);
        userService.persist(user4);
        userService.persist(user5);
    }

    private void getUsersByEmailProvider(String emailProvider) {
        List<String> result = this.userService.getAllUsers().stream()
                .filter(u -> u.getEmail().substring(u.getEmail().indexOf('@') + 1).equals(emailProvider))
                .map(u -> String.format("%s %s", u.getUsername(), u.getEmail()))
                .distinct()
                .collect(Collectors.toList());
        if (result.isEmpty()){
            System.out.println(String.format("No users found with email domain %s", emailProvider));
            return;
        }
        result.forEach(System.out::println);
    }

    private void removeInactiveUsers(String date) throws ParseException {
        Date currentDate = this.simpleDateFormat.parse(date);
        List<User> users = this.userService.getAllUsersThatHasNotBeenLoggedInAfterGivenDate(currentDate);

        users.forEach(u -> {
            u.setIsDeleted(true);
            this.userService.persist(u);
        });
        this.userService.deleteAll(users);

        System.out.println(String.format("%d users have been deleted", users.size()));
        String debug = "";
    }
}
