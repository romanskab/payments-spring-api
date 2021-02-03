package ua.training.payments.service;

import ua.training.payments.dto.AccountDto;
import ua.training.payments.model.User;
import ua.training.payments.model.enums.State;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    AccountDto createAccount(User user, State state, BigDecimal balance);

    List<AccountDto> getAllAccountsOfUser(User user);
}
