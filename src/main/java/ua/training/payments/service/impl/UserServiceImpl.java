package ua.training.payments.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.training.payments.dto.UserDto;
import ua.training.payments.model.User;
import ua.training.payments.repository.UserRepository;
import ua.training.payments.service.MappingService;
import ua.training.payments.service.UserService;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MappingService mappingService;

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = mappingService.mapUserDtoToUser(userDto);
        final User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("About to update user {}", principal);
        user.setId(principal.getId());
        user.setRole(principal.getRole());
        user.setState(principal.getState());
        user.setPassword(principal.getPassword());
        user = userRepository.save(user);
        log.info("Used with id {} successfully updated", user.getId());
        return mappingService.mapUserToUserDto(user);
    }

    @Override
    public void deleteUser(User user) {
        log.info("deleteUser: about to delete user with email {}", user.getEmail());
        SecurityContextHolder.clearContext();
        userRepository.delete(user);
    }

    @Override
    public boolean isUserExistsWithEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }
}
