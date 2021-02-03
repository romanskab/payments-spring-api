package ua.training.payments.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.training.payments.dto.AccountDto;
import ua.training.payments.model.Account;
import ua.training.payments.model.User;
import ua.training.payments.model.enums.State;
import ua.training.payments.repository.AccountRepository;
import ua.training.payments.service.AccountService;
import ua.training.payments.service.MappingService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final MappingService mappingService;

    @Override
    public AccountDto createAccount(User user, State state, BigDecimal balance) {
        Account account = new Account();
        account.setUser(user);
        account.setState(state);
        account.setBalance(balance);
        account = accountRepository.save(account);
        log.info("Account with id {} successfully created", account.getId());
        System.out.println("created account in service - " + account);
        return mappingService.mapAccountToAccountDto(account);
    }

    @Override
    public List<AccountDto> getAllAccountsOfUser(User user) {
        log.info("getAllAccountsOfUser: method is called");
//        final List<Account> accounts = accountRepository.findAllByUser(user);
//        System.out.println(accounts);

        final List<AccountDto> allAccountsOfUser = accountRepository.findAllByUser(user).stream()
                .map(mappingService::mapAccountToAccountDto)
                .collect(Collectors.toList());
        log.info("getAllAccountsOfUser: {} accounts found", allAccountsOfUser.size());
        return allAccountsOfUser;
    }

}
