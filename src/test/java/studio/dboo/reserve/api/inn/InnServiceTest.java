package studio.dboo.reserve.api.inn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import studio.dboo.reserve.api.accounts.AccountService;
import studio.dboo.reserve.api.inn.entity.Inn;
import studio.dboo.reserve.api.inn.entity.InnType;
import studio.dboo.reserve.test.WithAccount;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class InnServiceTest {

    @Autowired InnRepository innRepository;
    @Autowired
    AccountService accountService;
    @Autowired InnService innService;

    @Test
    @WithAccount
    public void createInn(){
        innService.createInn(Inn.builder()
                        .innName("inn-create-test-3")
                        .innType(InnType.MOTEL)
                        .account(accountService.getCurrentAccount())
                .build());
    }

}