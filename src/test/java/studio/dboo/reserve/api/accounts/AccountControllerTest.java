package studio.dboo.reserve.api.accounts;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import studio.dboo.reserve.api.accounts.entity.Account;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by dboo on 2021/11/04
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AccountControllerTest {

    @Autowired AccountService accountService;

    @BeforeEach
    public void createTestAccounts(){
        for(int i = 0; i<50; i++){
            createUser(
                    "test"+i,
                    "1234"
            );
        }
    }

    private Account createUser(String userId, String password) {
        Account account = Account.builder()
                .userId(userId)
                .password(password)
                .role("USER")
                .build();
        accountService.createAccount(account);
        return account;
    }

    @Test
    public void test(){
        System.out.println("test start!");
    }

    @Test
    @DisplayName("계정 생성 - 성공")
    public void createAccount(){
        Account account = Account.builder()
                .firstname("daehwan")
                .lastname("boo")
                .password("eoghks1@!2_").build();

    }


}