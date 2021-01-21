package ua.training.payments.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.training.payments.dto.UserDto;
import ua.training.payments.model.enums.Role;

public interface AuthService extends UserDetailsService {

    UserDto signIn(UserDto userDto);

    UserDto signUp(UserDto userDto, Role role);

    void signOut();

}
