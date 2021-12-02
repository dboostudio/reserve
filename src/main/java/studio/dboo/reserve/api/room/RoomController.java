package studio.dboo.reserve.api.room;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import studio.dboo.reserve.api.accounts.AccountService;
import studio.dboo.reserve.api.accounts.entity.Account;
import studio.dboo.reserve.api.room.dto.RoomForm;
import studio.dboo.reserve.api.room.entity.Room;
import studio.dboo.reserve.infra.exception.ReserveException;

import javax.validation.Valid;
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

    @PostMapping
    @ApiOperation(value = "createRoom", notes = "방 정보 생성")
    public ResponseEntity<Room> createRoom(@Valid RoomForm roomForm) throws ReserveException {
        roomService.createRoom(roomForm);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
