package studio.dboo.reserve.test;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import studio.dboo.reserve.module.accounts.AccountService;
import studio.dboo.reserve.module.accounts.entity.Account;

@RequiredArgsConstructor
public class WithAccountSecurityContextFactory implements WithSecurityContextFactory<WithAccount> {

    private final AccountService accountService;

    @Override
    public SecurityContext createSecurityContext(WithAccount withMember) {

        Account account = Account.builder()
                .userId("dboo.studio@gmail.com")
                .password("eoghks1@!2_")
                .build();

        accountService.login(account);

        return SecurityContextHolder.getContext();
    }
}
