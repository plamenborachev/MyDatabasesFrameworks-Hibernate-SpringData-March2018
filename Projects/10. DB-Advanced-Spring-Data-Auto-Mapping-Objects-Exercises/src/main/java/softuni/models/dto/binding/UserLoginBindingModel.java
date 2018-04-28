package softuni.models.dto.binding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserLoginBindingModel {

    private String email;
    private String password;
    private String fullName;

    public UserLoginBindingModel() {
    }

    public UserLoginBindingModel(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


}
