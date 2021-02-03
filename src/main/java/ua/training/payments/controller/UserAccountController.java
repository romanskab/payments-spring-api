package ua.training.payments.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.RestController;
import ua.training.payments.api.UserAccountApi;
import ua.training.payments.controller.assembler.AccountModelAssembler;
import ua.training.payments.controller.model.AccountModel;
import ua.training.payments.dto.AccountDto;
import ua.training.payments.model.User;
import ua.training.payments.model.enums.State;
import ua.training.payments.service.AccountService;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserAccountController implements UserAccountApi {

    private final AccountService accountService;
    private final AccountModelAssembler accountModelAssembler;

    @Override
    public AccountModel create(User user) {
        log.info("Creating account for user with email {}", user.getEmail());
        final AccountDto accountDto = accountService.createAccount(user, State.UNBLOCKED, BigDecimal.ZERO);
        System.out.println("accountDto - " + accountDto);
        return accountModelAssembler.toModel(accountDto);
    }

    @Override
    public CollectionModel<AccountModel> getAllAccountsOfUser(User user) {
        log.info("Get all user's accounts method is called");
        List<AccountDto> allAccountsOfUser = accountService.getAllAccountsOfUser(user);
        return accountModelAssembler.toCollectionModel(allAccountsOfUser);
    }
}
