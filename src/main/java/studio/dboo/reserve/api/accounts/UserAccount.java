package studio.dboo.reserve.api.accounts;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import studio.dboo.reserve.api.accounts.entity.Account;

import java.util.List;

/**
 * Created by dboo on 2021/11/04
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */
public class UserAccount extends User {
    private Account account;

    public UserAccount(Account account){
        super(account.getUserId(),
                account.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.account = account;

    }
}
