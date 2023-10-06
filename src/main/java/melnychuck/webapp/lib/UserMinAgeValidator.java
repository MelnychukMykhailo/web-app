package melnychuck.webapp.lib;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
public class UserMinAgeValidator implements ConstraintValidator<ValidByUserMinAgeIfPresent, LocalDate> {
    @Value("${minimum.age}")
    private Long minUserAge;

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext constraintValidatorContext) {
        if (birthDate == null) {
            return true;
        }
        LocalDate minBirthDate = LocalDate.now().minusYears(minUserAge);
        return birthDate.isBefore(minBirthDate);
    }
}
