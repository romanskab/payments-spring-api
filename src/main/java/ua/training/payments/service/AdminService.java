package ua.training.payments.service;

import ua.training.payments.dto.UserDto;

import java.util.List;

public interface AdminService {

    List<UserDto> getAllUsers();

}
