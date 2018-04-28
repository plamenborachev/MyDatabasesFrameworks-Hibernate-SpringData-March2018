package softuni.user_system.validators.email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {


    @Override
    public void initialize(Email constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (!email.matches("^[a-zA-Z0-9]+[a-zA-Z0-9-._]+[a-zA-Z0-9]+@[a-zA-z]+[a-zA-Z.-]+\\.+[a-zA-z]+$")) {
            return false;
        }
        return true;
    }
}
