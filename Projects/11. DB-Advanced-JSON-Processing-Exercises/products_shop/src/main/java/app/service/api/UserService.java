package app.service.api;

import app.models.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    void addToDb(User user);

    List<User> getAll();

    Long getCountOfUsers();

    User findUserById(Long id);

    List<User> getAllWithAtLeastOneSoldItemWithBuyer();

    List<User> getAllWithAtLeastOneSoldProduct();
}
