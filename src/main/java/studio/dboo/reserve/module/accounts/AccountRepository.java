package studio.dboo.reserve.module.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import studio.dboo.reserve.module.accounts.entity.Account;

import javax.transaction.Transactional;
import java.util.Optional;


@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUserId(String userId);

    Optional<Account> getAccountByUserId(String userId);

    boolean existsAccountByUserId(String userId);
}
