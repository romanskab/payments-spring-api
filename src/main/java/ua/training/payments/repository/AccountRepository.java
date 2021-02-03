package ua.training.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.training.payments.model.Account;
import ua.training.payments.model.User;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByUser(User user);
}
