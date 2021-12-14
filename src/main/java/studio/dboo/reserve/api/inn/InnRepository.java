package studio.dboo.reserve.api.inn;

import org.springframework.data.jpa.repository.JpaRepository;
import studio.dboo.reserve.api.accounts.entity.Account;
import studio.dboo.reserve.api.inn.entity.Inn;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by dboo on 2021/11/08
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */

@Transactional
public interface InnRepository extends JpaRepository<Inn, Long> {
    Inn findByAccount(Account account);

    Optional<Inn> findByAccountAndId(Account currentAccount, Long innId);
}
