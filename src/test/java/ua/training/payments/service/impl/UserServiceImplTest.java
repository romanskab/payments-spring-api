package ua.training.payments.service.impl;

import lombok.SneakyThrows;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.training.payments.dto.UserDto;
import ua.training.payments.model.User;
import ua.training.payments.repository.UserRepository;
import ua.training.payments.service.MappingService;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    public static final String TEST_EMAIL = "testemail@email.com";

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;
    @Spy
    private MappingService mappingService = new MappingServiceImpl();

    @Test
    void loadUserByUsernameTest() {
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.of(new User()));
        boolean userExistsWithEmail = userService.isUserExistsWithEmail(TEST_EMAIL);
        assertTrue(userExistsWithEmail);
    }

    @Test
    void deleteUserTest() {
        User testUser = createTestUser();
        doNothing().when(userRepository).delete(testUser);
        userService.deleteUser(testUser);

        verify(userRepository, times(1)).delete(testUser);
    }

    @Test
    void deleteUserWithExceptionTest() {
        doThrow(RuntimeException.class).when(userRepository).delete(any());
        assertThrows(RuntimeException.class,
                () -> userService.deleteUser(new User()));
    }

    @Test
    public void updateUserTest() {
        UserDto testUserDto = createTestUserDto();
        User testUser = createTestUser();
        when(userRepository.save(any())).thenReturn(testUser);

        UserDto userDto = userService.updateUser(testUserDto);

        assertThat(userDto, allOf(
                hasProperty("name", equalTo(testUserDto.getName())),
                hasProperty("surname", equalTo(testUserDto.getSurname())),
                hasProperty("email", equalTo(testUserDto.getEmail()))
        ));
    }

    private User createTestUser() {
        User user = new User();
        user.setName("TestName");
        user.setSurname("TestSurname");
        user.setEmail(TEST_EMAIL);
        return user;
    }

    @SneakyThrows
    private UserDto createTestUserDto() {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDto, createTestUser());
        return userDto;
    }

}