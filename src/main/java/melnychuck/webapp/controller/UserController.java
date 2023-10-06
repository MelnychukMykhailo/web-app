package melnychuck.webapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import melnychuck.webapp.dto.request.UserDetailsRequestDto;
import melnychuck.webapp.dto.request.UserRequestDto;
import melnychuck.webapp.dto.response.UserResponseDto;
import melnychuck.webapp.exception.DateOrderException;
import melnychuck.webapp.exception.UserNotFoundException;
import melnychuck.webapp.mapper.UserMapper;
import melnychuck.webapp.model.User;
import melnychuck.webapp.service.UserService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserResponseDto> findAllUsers() {
        return userService.findAllUsers().stream()
                .map(userMapper::mapToDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto create(@RequestBody @Valid UserRequestDto userRequestDto) {
        User user = userService.save(userMapper.mapToModel(userRequestDto));
        return userMapper.mapToDto(user);
    }

    @PutMapping("{id}")
    public UserResponseDto updateUserById(@PathVariable Long id,
                                      @RequestBody @Valid UserRequestDto userRequestDto) {
        User oldUser = userService.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found."));
        User updatedUser = userMapper.mapToModel(userRequestDto);
        updatedUser.setId(oldUser.getId());
        return userMapper.mapToDto(userService.save(updatedUser));
    }

    @PatchMapping("{id}")
    public UserResponseDto updateUserDetailsById(@PathVariable Long id,
                                      @RequestBody @Valid UserDetailsRequestDto userDetailsRequestDto) {
        User updatedUser = userService.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found."));
        if (userDetailsRequestDto.getEmail() != null) {
            updatedUser.setEmail(userDetailsRequestDto.getEmail());
        }
        if (userDetailsRequestDto.getFirstName() != null) {
            updatedUser.setFirstName(userDetailsRequestDto.getFirstName());
        }
        if (userDetailsRequestDto.getLastName() != null) {
            updatedUser.setLastName(userDetailsRequestDto.getLastName());
        }
        if (userDetailsRequestDto.getBirthDate() != null) {
            updatedUser.setBirthDate(userDetailsRequestDto.getBirthDate());
        }
        if (userDetailsRequestDto.getAddress() != null) {
            updatedUser.setAddress(userDetailsRequestDto.getAddress());
        }
        if (userDetailsRequestDto.getPhoneNumber() != null) {
            updatedUser.setPhoneNumber(userDetailsRequestDto.getPhoneNumber());
        }
        return userMapper.mapToDto(userService.save(updatedUser));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        if (!userService.existsById(id)) {
            throw new UserNotFoundException("User with id " + id + " not found.");
        }
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("by_birth_date")
    public List<UserResponseDto> findAllUsersInRange(@RequestParam  LocalDate from,
                                                     @RequestParam  LocalDate to) {
        if (from.isAfter(to)) {
            throw new DateOrderException("Incorrect date range format: "
                    + from + " can`t be before " + to + ".");

        }
        return userService.findUsersByBirthDateAfterAndBirthDateBefore(from, to).stream()
                .map(userMapper::mapToDto)
                .toList();
    }

}
