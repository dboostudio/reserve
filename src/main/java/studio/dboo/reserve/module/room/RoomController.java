package studio.dboo.reserve.module.room;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studio.dboo.reserve.module.room.entity.Room;

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

    @GetMapping("/{innName}")
    @ApiOperation(value = "getRoomList", notes = "방 정보 조회")
    public ResponseEntity<List<Room>> getRoomList(@PathVariable String innName){
        List<Room> roomList = roomService.getRoomList(innName);
        return ResponseEntity.status(HttpStatus.OK).body(roomList);
    }

}
