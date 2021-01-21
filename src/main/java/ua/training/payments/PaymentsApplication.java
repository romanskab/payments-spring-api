package ua.training.payments;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.training.payments.dto.UserDto;
import ua.training.payments.model.enums.Role;
import ua.training.payments.service.AuthService;

@SpringBootApplication
public class PaymentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentsApplication.class, args);
    }

    @Bean
    public CommandLineRunner demoAdmin(AuthService authService,
                                       @Value("${app.auth.admin.password}") String password,
                                       @Value("${app.auth.admin.email}") String email) {
        return args -> {
            UserDto userDto = new UserDto();
            userDto.setSurname("Admin");
            userDto.setName("Anton");
            userDto.setEmail(email);
            userDto.setPassword(password);
            authService.signUp(userDto, Role.ROLE_ADMIN);
        };
    }

}
