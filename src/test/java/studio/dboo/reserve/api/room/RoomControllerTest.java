package studio.dboo.reserve.api.room;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import studio.dboo.reserve.api.accounts.AccountService;
import studio.dboo.reserve.api.inn.InnService;
import studio.dboo.reserve.api.inn.entity.Inn;
import studio.dboo.reserve.api.room.dto.RoomForm;
import studio.dboo.reserve.api.room.entity.Room;
import studio.dboo.reserve.test.MockMvcTest;
import studio.dboo.reserve.test.WithAccount;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class RoomControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired InnService innService;
    @Autowired ObjectMapper objectMapper;
    @Autowired AccountService accountService;

    public void test() {
        System.out.println("aaa");
    }

    @WithAccount
    @Test
    public void getRoomList() throws Exception {
        Inn inn = innService.getMyFirstInn();
        mockMvc.perform(get("/api/room/"+inn.getId())
                        .with(csrf()))
                .andDo(print());
    }

    @WithAccount
    @Test
    public void createRoomTest() throws Exception {
        Inn inn = innService.getMyFirstInn();
        Room room = Room.builder()
                .roomName("701호")
                .numberOfBedroom(3)
                .quota(20)
                .comment("최근 추가된 방")
                .build();
        RoomForm roomForm = RoomForm.builder()
                .innId(inn.getId())
                .room(room)
                .build();
        mockMvc.perform(post("/api/room")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(roomForm))
                        .with(csrf()))
                .andDo(print());
    }
}