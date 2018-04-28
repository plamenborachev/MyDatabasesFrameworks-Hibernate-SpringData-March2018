package softuni.models.dto.binding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegisterBindingModel {

    private static final int PASSWORD_MIN_LENGTH = 6;
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;

    public UserRegisterBindingModel() {
    }

    public UserRegisterBindingModel(String email, String password, String confirmPassword, String fullName) {
        this.setEmail(email);
        this.setPassword(password);
        this.setConfirmPassword(confirmPassword);
        this.fullName = fullName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        if (!email.matches("^[a-zA-Z0-9]+[a-zA-Z0-9-._]+[a-zA-Z0-9]+@[a-zA-z]+[a-zA-Z.-]+\\.+[a-zA-z]+$")){
            throw new IllegalArgumentException("Incorrect email.");
        }
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        if (password.length() < PASSWORD_MIN_LENGTH){
            throw new IllegalArgumentException("Password should be at least 6 symbols");
        }
        Pattern regex = Pattern.compile("[a-z]");
        Matcher matcher = regex.matcher(password);
        if (!matcher.find()){
            throw new IllegalArgumentException("Password should contain lowercase letter");
        }
        regex = Pattern.compile("[A-Z]");
        matcher = regex.matcher(password);
        if (!matcher.find()){
            throw new IllegalArgumentException("Password should contain uppercase letter");
        }
        regex = Pattern.compile("[0-9]");
        matcher = regex.matcher(password);
        if (!matcher.find()){
            throw new IllegalArgumentException("Password should contain digit");
        }
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        if (!this.password.equals(confirmPassword)){
            throw new IllegalArgumentException("Passwords should match");
        }
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


}
