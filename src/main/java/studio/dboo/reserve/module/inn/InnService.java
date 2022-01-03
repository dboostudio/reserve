package studio.dboo.reserve.module.inn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studio.dboo.reserve.module.accounts.AccountRepository;
import studio.dboo.reserve.module.accounts.entity.Account;
import studio.dboo.reserve.module.inn.entity.Inn;

import java.util.List;
import java.util.Optional;

/**
 * Created by dboo on 2021/11/08
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */

@Service
@RequiredArgsConstructor
public class InnService {

    private final AccountRepository accountRepository;
    private final InnRepository innRepository;

    public Object getInnList(Account account) {
        Optional<Account> savedAccount = accountRepository.getAccountByUserId(account.getUserId());
        List<Inn> innList = savedAccount.orElseThrow().getInns();
        return innList;
    }

    public Inn getInn(Account account, String innName) {
        Optional<Account> savedAccount = accountRepository.getAccountByUserId(account.getUserId());
        Optional<Inn> inn;
        if(innName == null){
            inn = Optional.ofNullable(savedAccount.orElseThrow().getInns().get(0));
        } else {
            inn = innRepository.findByAccountAndInnName(account, innName);
        }
        return inn.orElseThrow();
    }

    public Inn getInn(Account account) {
        Optional<Account> savedAccount = accountRepository.getAccountByUserId(account.getUserId());
        return savedAccount.orElseThrow().getInns().get(0);
    }

    public Inn createInn(Account account, Inn inn) {
        inn.setAccount(account);
        return innRepository.save(inn);
    }

    public Inn updateInn(Account account, Inn inn) {
        Optional<Inn> savedInn = innRepository.findByAccountAndInnName(account, inn.getInnName());
        return savedInn.orElseThrow();
    }
}
