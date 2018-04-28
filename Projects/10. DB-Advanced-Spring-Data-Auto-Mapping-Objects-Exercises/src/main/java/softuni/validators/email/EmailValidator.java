package softuni.validators.email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<Email, String> {


    @Override
    public void initialize(Email constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email.matches("^[a-zA-Z0-9]+[a-zA-Z0-9-._]+[a-zA-Z0-9]+@[a-zA-z]+[a-zA-Z.-]+\\.+[a-zA-z]+$");
    }
}
