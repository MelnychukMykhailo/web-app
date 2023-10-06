package melnychuck.webapp.service;

import melnychuck.webapp.model.User;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);

    void deleteById(Long id);

    List<User> findAllUsers();

    boolean existsByEmail(String email);

    Optional<User> findUserById(Long id);

    boolean existsById(Long id);

    List<User> findUsersByBirthDateAfterAndBirthDateBefore(LocalDate after, LocalDate before);
}
