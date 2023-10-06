package melnychuck.webapp.mapper;

import melnychuck.webapp.dto.request.UserRequestDto;
import melnychuck.webapp.dto.response.UserResponseDto;
import melnychuck.webapp.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto mapToDto(User user) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setEmail(user.getEmail());
        responseDto.setFirstName(user.getFirstName());
        responseDto.setLastName(user.getLastName());
        responseDto.setBirthDate(user.getBirthDate());
        responseDto.setAddress(user.getAddress());
        responseDto.setPhoneNumber(user.getPhoneNumber());
        return responseDto;
    }

    public User mapToModel(UserRequestDto requestDto) {
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setBirthDate(requestDto.getBirthDate());
        user.setAddress(requestDto.getAddress());
        user.setPhoneNumber(requestDto.getPhoneNumber());
        return user;
    }
}
