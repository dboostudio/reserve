package studio.dboo.reserve.api.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studio.dboo.reserve.api.accounts.AccountService;
import studio.dboo.reserve.api.inn.InnRepository;
import studio.dboo.reserve.api.inn.InnService;
import studio.dboo.reserve.api.inn.entity.Inn;
import studio.dboo.reserve.api.room.dto.RoomForm;
import studio.dboo.reserve.api.room.entity.Room;
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
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final InnService innService;
    private final InnRepository innRepository;
    private final AccountService accountService;

    public List<Room> getRoomList(Long innId) throws ReserveException {
//        Inn inn = innService.getInnByInnId(innId);
        Optional<Inn> inn = innRepository.findByAccountAndId(accountService.getCurrentAccount(), innId);
        List<Room> roomList = inn.orElseThrow(() -> new ReserveException("계정에 해당하는 숙소가 존재하지 않습니다.")).getRooms();
        return roomList;
    }

    public Room createRoom(RoomForm roomForm) throws ReserveException {
        Optional<Inn> inn = innRepository.findByAccountAndId(accountService.getCurrentAccount(), roomForm.getInnId());
        List<Room> roomList = inn.orElseThrow(() -> new ReserveException("존재하지 않는 숙소입니다.")).getRooms();

        if(roomList.stream().anyMatch(room -> room.getRoomName() == roomForm.getRoom().getRoomName())){
                throw new ReserveException("이미 존재하는 방 이름입니다.");
        }

        Room room = roomForm.getRoom();
        room.setInn(inn.get());

        return roomRepository.save(room);
    }

    public void updateRoom(RoomForm roomForm) throws ReserveException {
        // 본인이 소유하고있는 방인지 확인
        isMyRoom(roomForm);
        roomRepository.save(roomForm.getRoom());
    }

    public void deleteRoom(RoomForm roomForm) throws ReserveException {
        // 본인 소유의 방인지 확인
        isMyRoom(roomForm);
        roomRepository.delete(roomForm.getRoom());
    }

    public void isMyRoom(RoomForm roomForm) throws ReserveException {
        if(!getRoomList(roomForm.getInnId()).stream()
                .anyMatch(room -> room.getId() == roomForm.getRoom().getId())){
            throw new ReserveException("본인의 방만 수정할 수 있습니다.");
        }
    }

}
