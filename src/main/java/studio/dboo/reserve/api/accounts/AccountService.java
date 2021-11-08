package studio.dboo.reserve.api.accounts;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import studio.dboo.reserve.api.accounts.entity.Account;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * Created by dboo on 2021/11/04
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    /** Constant */
    private static final String CANNOT_FIND_USER = "의 이메일로 가입된 계정이 없습니다.";
    private static final String ALREADY_EXIST_USER = "이미 가입한 이메일 계정입니다.";
    private static final String PASSWORD_NOT_MATCH = "비밀번호가 일치하지 않습니다.";

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Account> byUserId = accountRepository.findByUserId(userId);
        Account account = byUserId.orElseThrow(()-> new UsernameNotFoundException(userId));
        return new User(account.getUserId(),
                account.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public Account getAccount(String userId) {
        Optional<Account> byUserId = accountRepository.findByUserId(userId);
        Account account = byUserId.orElseThrow(()-> new UsernameNotFoundException(userId));
        return account;
    }

    public Account createAccount(Account account) {
        if (accountRepository.existsAccountByUserId(account.getUserId()) == true) {
            throw new RuntimeException(ALREADY_EXIST_USER);
        }
        account.encodePassword(passwordEncoder);
        account.setRole("USER");
        return accountRepository.save(account);
    }

    public void updateAccount(Account account) {
         accountRepository.save(account);
    }

    public void deleteAccount(Account account) {
        accountRepository.save(account);
    }

    public Boolean isMyself(Account account) {
        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(account.getUserId());
        System.out.println(principal.getName());
        Boolean isMyself = account.getUserId().equals(principal.getName());
        return isMyself;
    }

    public void login(Account account) {
        // 가입여부, 패스워드 체크
        Optional<Account> byUserId = accountRepository.findByUserId(account.getUserId());
        Account savedAccount = byUserId.orElseThrow(()-> new UsernameNotFoundException(account.getUserId()));

        if(!passwordEncoder.matches(account.getPassword(),savedAccount.getPassword())){
            throw new BadCredentialsException(PASSWORD_NOT_MATCH);
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(account),
                account.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    public void logout(Account account) {
        isMyself(account);

    }
}
