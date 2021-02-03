package ua.training.payments.service;

import ua.training.payments.dto.UserDto;
import ua.training.payments.model.User;

public interface UserService {

    UserDto updateUser(UserDto userDto);

    void deleteUser(User user);

    boolean isUserExistsWithEmail(String email);

    User findById(Long id);

}
