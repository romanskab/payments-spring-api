package ua.training.payments.service;

import ua.training.payments.dto.AccountDto;
import ua.training.payments.dto.UserDto;
import ua.training.payments.model.Account;
import ua.training.payments.model.User;

public interface MappingService {

    User mapUserDtoToUser(UserDto userDto);

    UserDto mapUserToUserDto(User user);

    AccountDto mapAccountToAccountDto(Account account);

}
