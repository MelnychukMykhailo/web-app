package melnychuck.webapp.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import melnychuck.webapp.lib.ValidByEmailIfPresent;
import melnychuck.webapp.lib.ValidByUserMinAgeIfPresent;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class UserRequestDto {
    @NotNull
    @ValidByEmailIfPresent
    private String email;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @ValidByUserMinAgeIfPresent
    private LocalDate birthDate;
    private String address;
//   Can`t use pattern, because it can be internal company number.
    private String phoneNumber;
}
