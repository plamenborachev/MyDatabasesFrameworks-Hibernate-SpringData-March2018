package softuni.user_system.services.interfaces;

import softuni.user_system.entities.User;

import java.util.Date;
import java.util.List;

public interface UserService {

    void persist(User user);

    List<User> getAllUsers();

    List<User> getAllUsersThatHasNotBeenLoggedInAfterGivenDate(Date date);

    void deleteAll(List<User> users);

}
