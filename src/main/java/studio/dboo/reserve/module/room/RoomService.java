package studio.dboo.reserve.module.room;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import studio.dboo.reserve.module.accounts.AccountService;
import studio.dboo.reserve.module.accounts.entity.Account;
import studio.dboo.reserve.module.inn.InnRepository;
import studio.dboo.reserve.module.inn.entity.Inn;
import studio.dboo.reserve.module.room.entity.Room;

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

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Account account = accountService.getAccount(username);
        Optional<Inn> inn = innRepository.findByAccountAndInnName(account, innName);
        List<Room> roomList = inn.orElseThrow().getRooms();
        return roomList;
    }
}
