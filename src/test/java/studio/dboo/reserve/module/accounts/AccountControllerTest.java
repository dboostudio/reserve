package studio.dboo.reserve.module.accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
    @Autowired ObjectMapper objectMapper;
    @Autowired MockMvc mockMvc;

    final static String TEST_USER_ID_PASSWORD_JSON = "{\"userId\":\"test@gmail.com\",\"password\":\"12341234\"}";

    @BeforeAll
    static void beforeAll(){
        System.out.println("before All");
    }

    @Test
    @DisplayName("계정 생성 성공")
    void postAccount() throws Exception {
        mockMvc.perform(post("/api/account")
                        .content(TEST_USER_ID_PASSWORD_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("로그인")
    void login() throws Exception {
        postAccount();
        mockMvc.perform(post("/api/account/login")
                        .content(TEST_USER_ID_PASSWORD_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("본인 계정 조회")
    void getAccount() throws Exception {
        login();
        mockMvc.perform(get("/api/account")
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isOk());
    }


}