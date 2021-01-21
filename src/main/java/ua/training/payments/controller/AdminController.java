package ua.training.payments.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.RestController;
import ua.training.payments.api.AdminApi;
import ua.training.payments.controller.assembler.UserModelAssembler;
import ua.training.payments.controller.model.UserModel;
import ua.training.payments.dto.UserDto;
import ua.training.payments.service.AdminService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminController implements AdminApi {

    private final AdminService adminService;
    private final UserModelAssembler modelAssembler;

    @Override
    public CollectionModel<UserModel> getAllUsers() {
        log.info("Get all users method is called");
        List<UserDto> allUsers = adminService.getAllUsers();
        return modelAssembler.toCollectionModel(allUsers);
    }

}
