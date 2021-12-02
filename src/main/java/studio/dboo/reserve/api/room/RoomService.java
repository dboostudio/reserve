package studio.dboo.reserve.api.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studio.dboo.reserve.api.accounts.AccountService;
import studio.dboo.reserve.api.accounts.entity.Account;
import studio.dboo.reserve.api.inn.InnRepository;
import studio.dboo.reserve.api.inn.entity.Inn;
import studio.dboo.reserve.api.room.dto.RoomForm;
import studio.dboo.reserve.api.room.entity.Room;
import studio.dboo.reserve.infra.exception.ReserveException;

import javax.validation.Valid;
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
public class RoomService {

    private final RoomRepository roomRepository;
    private final InnRepository innRepository;
    private final AccountService accountService;

    public List<Room> getRoomList(String innName) {
        Account account = accountService.getCurrentAccount();
        Optional<Inn> inn = innRepository.findByAccountAndInnName(account, innName);
        List<Room> roomList = inn.orElseThrow().getRooms();
        return roomList;
    }

    public void createRoom(RoomForm roomForm) throws ReserveException {
        // 이미 존재하는 방이름이라면, 에러 리턴
        List<Room> roomList = getRoomList(roomForm.getInnName());
        for(Room room : roomList){
            if(room.getRoomName().equals(roomForm.getRoom().getRoomName())){
                throw new ReserveException("이미 존재하는 방 이름입니다.");
            }
        }
        roomRepository.save(roomForm.getRoom());
    }

    public void updateRoom(RoomForm roomForm) throws ReserveException {
        // 본인이 소유하고있는 방인지 확인

    }
}
