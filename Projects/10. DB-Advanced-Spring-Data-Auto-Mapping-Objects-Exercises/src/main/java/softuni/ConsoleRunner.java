package softuni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.models.dto.binding.UserLoginBindingModel;
import softuni.models.dto.binding.UserRegisterBindingModel;
import softuni.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private UserService userService;

    @Autowired
    public ConsoleRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... strings) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        strings = reader.readLine().split("\\|");
        String command = strings[0];

        switch (command) {
            case "RegisterUser":
                String email = strings[1];
                String password = strings[2];
                String confirmPassword = strings[3];
                String fullName = strings[4];
                UserRegisterBindingModel userRegisterBindingModel
                        = new UserRegisterBindingModel(email, password, confirmPassword, fullName);
                this.userService.registerUser(userRegisterBindingModel);
                break;
            case "LoginUser":
                email = strings[1];
                password = strings[2];
                UserLoginBindingModel userLoginBindingModel = new UserLoginBindingModel(email, password);
                this.userService.login(userLoginBindingModel);
                break;
            case "Logout":
                this.userService.logout();
                break;
        }
    }
}

