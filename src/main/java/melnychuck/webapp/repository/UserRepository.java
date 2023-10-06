package melnychuck.webapp.repository;

import melnychuck.webapp.model.User;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsById(Long id);
    Optional<User> findUserById(Long id);

    List<User> findUsersByBirthDateAfterAndBirthDateBefore(LocalDate after, LocalDate before);

}
