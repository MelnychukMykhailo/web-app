package melnychuck.webapp.service;

import lombok.RequiredArgsConstructor;
import melnychuck.webapp.model.User;
import melnychuck.webapp.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean existsByEmail(String email) {
        return  userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public List<User> findUsersByBirthDateAfterAndBirthDateBefore(LocalDate after, LocalDate before) {
        return userRepository.findUsersByBirthDateAfterAndBirthDateBefore(after, before);
    }


}
