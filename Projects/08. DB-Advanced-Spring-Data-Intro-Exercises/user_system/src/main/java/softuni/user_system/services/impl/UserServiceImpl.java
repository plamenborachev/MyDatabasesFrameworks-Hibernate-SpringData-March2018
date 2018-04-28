package softuni.user_system.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.user_system.entities.User;
import softuni.user_system.repositories.UserRepository;
import softuni.user_system.services.interfaces.UserService;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void persist(User user) {
        this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public List<User> getAllUsersThatHasNotBeenLoggedInAfterGivenDate(Date date) {
        return this.userRepository.findAllByLastTimeLoggedInBefore(date);
    }

    @Override
    public void deleteAll(List<User> users) {
        this.userRepository.deleteAll(users);
    }

}
