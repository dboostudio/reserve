package studio.dboo.reserve.api.accounts;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import studio.dboo.reserve.api.accounts.entity.Account;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;


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

    final static String TEST_USER_ID = "dboo.studio@gmail.com";
    final static String TEST_USER_PASSWORD = "eoghks1@!2_";

    @Test
    @DisplayName("계정 조회 (관리자 계정만 가능)")
    void getAccount() throws Exception {
        mockMvc.perform(get("/api/account")).andExpect(status().isOk());

    }

    @Test
    @DisplayName("계정 생성 성공")
    void postAccount() throws Exception {
        String content = objectMapper.writeValueAsString(
                Account.builder()
                        .userId(TEST_USER_ID)
                        .password(TEST_USER_PASSWORD)
                        .build());
        mockMvc.perform(post("/api/account")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(unauthenticated());
    }

    @Test
    @DisplayName("계정 생성 실패 - 중복아이디")
    void postAccountFail() throws Exception {
        mockMvc.perform(post("/api/account")
                        .param("userId", "")
                        .param("", ""))
                .andExpect(status().isOk())
                .andExpect(unauthenticated());
    }


}