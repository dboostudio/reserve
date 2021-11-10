package studio.dboo.reserve.api.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import studio.dboo.reserve.api.inn.InnRepository;
import studio.dboo.reserve.api.room.entity.Room;

import java.util.List;

/**
 * Created by dboo on 2021/11/08
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */

@Service
@RequiredArgsConstructor
public class RoomService {

    private final InnRepository innRepository;
    private final RoomRepository roomRepository;

    public List<Room> getRoomList() {

        return null;
    }
}
