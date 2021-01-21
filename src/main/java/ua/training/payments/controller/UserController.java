package ua.training.payments.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ua.training.payments.api.UserApi;
import ua.training.payments.controller.assembler.UserModelAssembler;
import ua.training.payments.controller.model.UserModel;
import ua.training.payments.dto.UserDto;
import ua.training.payments.model.User;
import ua.training.payments.service.MappingService;
import ua.training.payments.service.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;
    private final UserModelAssembler modelAssembler;
    private final MappingService mappingService;


    @Override
    public UserModel getUser(User user) {
        log.info("getUser: with id {}", user.getId());
        UserDto userDto = mappingService.mapUserToUserDto(user);
        return modelAssembler.toModel(userDto);
    }

    @Override
    public UserModel updateUser(UserDto userDto) {
        log.info("updateUser controller is called");
        UserDto user = userService.updateUser(userDto);
        return modelAssembler.toModel(user);
    }

    @Override
    public ResponseEntity<Void> deleteUser(User user) {
        log.info("deleteUser: with id {}", user.getId());
        userService.deleteUser(user);
        return ResponseEntity.noContent().build();
    }
}
