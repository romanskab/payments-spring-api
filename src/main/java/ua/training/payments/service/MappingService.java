package ua.training.payments.service;

import ua.training.payments.dto.UserDto;
import ua.training.payments.model.User;

public interface MappingService {

    User mapUserDtoToUser(UserDto userDto);

    UserDto mapUserToUserDto(User user);

}
