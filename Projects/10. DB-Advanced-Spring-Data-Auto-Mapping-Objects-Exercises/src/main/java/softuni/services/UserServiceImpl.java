package softuni.services;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.models.dto.binding.UserLoginBindingModel;
import softuni.models.dto.binding.UserRegisterBindingModel;
import softuni.models.dto.view.SuccessLogoutUser;
import softuni.models.entities.User;
import softuni.repositories.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegisterBindingModel model) {
        User user = this.modelMapper.map(model, User.class);
        if (this.userRepository.count() == 0) {
            user.setAdministrator(true);
        }
        this.userRepository.saveAndFlush(user);
        System.out.println(String.format("%s was registered", model.getFullName()));
    }

    @Override
    public void login(UserLoginBindingModel model) throws IllegalAccessException {
        if (this.userRepository.getByEmail(model.getEmail()) == null){
            throw new IllegalAccessException("Incorrect username");
        }
        if (!this.userRepository.getByEmail(model.getEmail()).getPassword().equals(model.getPassword())){
            throw new IllegalAccessException("Incorrect password");
        }
        User user = this.userRepository.getByEmail(model.getEmail());
        model.setFullName(user.getFullName());
        if (!user.isLoggedIn()){
            user.setLoggedIn(true);
            System.out.println(String.format("Successfully logged in %s", model.getFullName()));
        } else {
            System.out.println(String.format("%s already logged id", model.getFullName()));
        }
    }

    @Override
    public void logout() {
        List<User> users = this.userRepository.getAllByLoggedInIsTrue();
        if (users.size() == 0){
            System.out.println("Cannot log out. No user was logged in.");
            return;
        }
        users.forEach(u -> {
            u.setLoggedIn(false);
            SuccessLogoutUser user = this.modelMapper.map(u, SuccessLogoutUser.class);
            System.out.println(String.format("User %s successfully logged out", user.getFullName()));
        });
    }
}
