package ua.training.payments.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.training.payments.dto.UserDto;
import ua.training.payments.model.enums.Role;
import ua.training.payments.model.enums.State;
import ua.training.payments.model.payload.JwtResponse;

public interface AuthService extends UserDetailsService {

    JwtResponse signIn(UserDto userDto);

    UserDto signUp(UserDto userDto, Role role, State state);

    void signOut();

}
