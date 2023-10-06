package melnychuck.webapp.lib;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import melnychuck.webapp.service.UserService;

@RequiredArgsConstructor
public class EmailIfPresentValidator implements ConstraintValidator<ValidByEmailIfPresent, String> {
    private final String EMAIL_VALIDATION_REGEX = "^(?:[^.\\s])\\S*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
    private final UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (email == null) {
            return true;
        }
        return email.matches(EMAIL_VALIDATION_REGEX) && !userService.existsByEmail(email);
    }
}
