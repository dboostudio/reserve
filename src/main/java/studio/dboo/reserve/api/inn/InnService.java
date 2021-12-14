package studio.dboo.reserve.api.inn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studio.dboo.reserve.api.accounts.AccountService;
import studio.dboo.reserve.api.accounts.entity.Account;
import studio.dboo.reserve.api.inn.entity.Inn;
import studio.dboo.reserve.infra.exception.ReserveException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by dboo on 2021/11/08
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */

@Service
@Transactional
@RequiredArgsConstructor
public class InnService {

    private final AccountService accountService;
    private final InnRepository innRepository;

    public List<Inn> getInnList() throws ReserveException {
        List<Inn> innList = accountService.getCurrentAccount().getInns();
        if(innList == null){
            throw new ReserveException("등록된 숙소가 없습니다.");
        }
        return innList;
    }

    public Inn getInnByInnName(String innName) {
        Account account = accountService.getCurrentAccount();
        List<Inn> innList = account.getInns();
        for(Inn inn : innList){
            if(inn.getInnName().equals(innName)){
                return inn;
            }
        }
        return null;
    }

    public void createInn(Inn inn) {
        inn.setAccount(accountService.getCurrentAccount());
        innRepository.save(inn);
    }

    public Inn updateInn(Inn inn) {
        Optional<Inn> savedInn = innRepository.findByAccountAndId(accountService.getCurrentAccount(), inn.getId());
        return savedInn.orElseThrow();
    }

    public Inn getMyFirstInn() {
        Account account = accountService.getCurrentAccount();
        return account.getInns().get(0);
    }

    public Inn getInnByInnId(Long innId) {
        Account account = accountService.getCurrentAccount();
        return (Inn) account.getInns().stream().filter(inn -> inn.getId() == innId);
    }
}
