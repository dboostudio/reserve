package studio.dboo.reserve.api.inn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import studio.dboo.reserve.api.accounts.entity.Account;
import studio.dboo.reserve.api.inn.entity.Inn;

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

    private final InnRepository innRepository;

    public Inn getInn(Account account) {
        return null;
    }
}
