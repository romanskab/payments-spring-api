package ua.training.payments;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import ua.training.payments.controller.model.UserModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PaymentsApplicationTests {

    @LocalServerPort
    private int port;

    @Value("${app.auth.admin.password}")
    private String password;
    @Value("${app.auth.admin.email}")
    private String email;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        UserModel forObject = restTemplate.withBasicAuth(email, password)
                .getForObject("http://localhost:" + port + "/api/v1/user", UserModel.class);

        assertEquals(forObject.getUserDto().getEmail(), email);
    }

}
