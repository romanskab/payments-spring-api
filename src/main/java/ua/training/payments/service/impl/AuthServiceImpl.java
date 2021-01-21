package ua.training.payments.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.training.payments.dto.UserDto;
import ua.training.payments.model.User;
import ua.training.payments.model.enums.Role;
import ua.training.payments.repository.UserRepository;
import ua.training.payments.service.AuthService;
import ua.training.payments.service.MappingService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MappingService mappingService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Unable to find user email!"));
    }

    @Override
    public UserDto signIn(UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getEmail(),
                        userDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        return mappingService.mapUserToUserDto(user);
    }

    @Override
    public UserDto signUp(UserDto userDto, Role role) {
        User user = mappingService.mapUserDtoToUser(userDto);
        log.info("createUser: about to register a new user with email {}", user.getEmail());

        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        log.info("Used with id {} successfully registered", user.getId());

        return signIn(userDto);
    }

    @Override
    public void signOut() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("Singing out user with email {}", user.getEmail());
        SecurityContextHolder.clearContext();
    }

}
