package melnychuck.webapp.dto.request;

import lombok.Getter;
import lombok.Setter;
import melnychuck.webapp.lib.ValidByEmailIfPresent;
import melnychuck.webapp.lib.ValidByUserMinAgeIfPresent;
import java.time.LocalDate;

@Getter
@Setter
public class UserDetailsRequestDto {
   @ValidByEmailIfPresent
    private String email;
    private String firstName;
    private String lastName;
    @ValidByUserMinAgeIfPresent
    private LocalDate birthDate;
    private String address;
    private String phoneNumber;
}
