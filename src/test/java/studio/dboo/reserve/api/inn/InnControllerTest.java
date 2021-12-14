package studio.dboo.reserve.api.inn;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import studio.dboo.reserve.api.accounts.AccountService;
import studio.dboo.reserve.api.inn.entity.Inn;
import studio.dboo.reserve.api.inn.entity.InnType;
import studio.dboo.reserve.test.MockMvcTest;
import studio.dboo.reserve.test.WithAccount;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class InnControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired AccountService accountService;
    @Autowired ObjectMapper objectMapper;
    @Autowired
    private WebApplicationContext wac;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))  // 필터 추가
                .build();
    }

    @Test
    @DisplayName("숙소 정보 생성")
    @WithAccount
    public void createInn() throws Exception {

        Inn inn = Inn.builder()
                .innType(InnType.HOMESTAY)
                .innName("생성한 숙소33")
                .build();

        mockMvc.perform(post("/api/inn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inn))
                        .with(csrf()))
                .andDo(print());
    }

    @Test
    @WithAccount
    public void getInnList() throws Exception {
        mockMvc.perform(get("/api/inn/list")
                        .with(csrf()))
                .andDo(print());
    }
}