package studio.dboo.reserve.api.room;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.dboo.reserve.api.room.entity.Room;

import java.util.List;

/**
 * Created by dboo on 2021/11/08
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;


    @GetMapping
    public ResponseEntity<List<Room>> getRoom(){

        List<Room> roomList = roomService.getRoomList();

        return ResponseEntity.status(HttpStatus.OK).body(roomList);

    }

    @PostMapping
}
