package app.service.impl;

import app.models.entity.User;
import app.repository.UserRepository;
import app.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addToDb(User user) {
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Long getCountOfUsers() {
        return this.userRepository.count();
    }

    @Override
    public User findUserById(Long id){
        return this.userRepository.getById(id);
    }

    @Override
    public List<User> getAllWithAtLeastOneSoldItemWithBuyer() {
        return this.userRepository.getAllByProductsSoldWithBuyer();
    }

    @Override
    public List<User> getAllWithAtLeastOneSoldProduct() {
        return this.userRepository.getAllByProductsSoldAfterOrderByProductsSoldDescLastName();
    }
}
