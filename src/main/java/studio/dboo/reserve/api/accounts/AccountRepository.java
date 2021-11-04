package studio.dboo.reserve.api.accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import studio.dboo.reserve.api.accounts.entity.Account;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by dboo on 2021/11/04
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUserId(String userId);

    Optional<Account> getAccountByUserId(String userId);

    boolean existsAccountByUserId(String userId);
}
