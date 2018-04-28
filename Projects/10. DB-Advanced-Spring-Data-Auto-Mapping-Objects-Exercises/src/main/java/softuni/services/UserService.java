package softuni.services;

import softuni.models.dto.binding.UserLoginBindingModel;
import softuni.models.dto.binding.UserRegisterBindingModel;

public interface UserService {

    void registerUser(UserRegisterBindingModel model);

    void login(UserLoginBindingModel model) throws IllegalAccessException;

    void logout();

}
