package ua.training.payments.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ua.training.payments.api.AuthApi;
import ua.training.payments.controller.assembler.UserModelAssembler;
import ua.training.payments.controller.model.UserModel;
import ua.training.payments.dto.UserDto;
import ua.training.payments.model.enums.Role;
import ua.training.payments.model.enums.State;
import ua.training.payments.model.payload.JwtResponse;
import ua.training.payments.service.AuthService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;
    private final UserModelAssembler modelAssembler;

    @Override
    public JwtResponse signIn(UserDto inUserDto) {
        log.info("Singing in user with email {}", inUserDto.getEmail());
        return authService.signIn(inUserDto);
    }

    @Override
    public UserModel signUp(UserDto inUserDto) {
        log.info("Registering user with email {}", inUserDto.getEmail());
        UserDto outUserDto = authService.signUp(inUserDto, Role.ROLE_CLIENT, State.UNBLOCKED);
        return modelAssembler.toModel(outUserDto);
    }

    @Override
    public ResponseEntity<Void> signOut() {
        System.out.println("AuthController.signOut() started!");
        authService.signOut();
        return ResponseEntity.noContent().build();
    }

}
