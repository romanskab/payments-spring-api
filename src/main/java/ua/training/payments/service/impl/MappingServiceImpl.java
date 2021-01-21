package ua.training.payments.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ua.training.payments.dto.UserDto;
import ua.training.payments.model.User;
import ua.training.payments.service.MappingService;

@Slf4j
@Service
public class MappingServiceImpl implements MappingService {
    @Override
    public User mapUserDtoToUser(UserDto userDto) {
        log.debug("mapUserDtoToUser: map to User from UserDto: {}", userDto);
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }

    @Override
    public UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        userDto.setPassword(null);
        log.debug("mapUserToUserDto: map from User to UserDto: {}", userDto);
        return userDto;
    }
}
