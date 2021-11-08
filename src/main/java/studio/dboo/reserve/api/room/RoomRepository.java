package studio.dboo.reserve.api.room;

import org.springframework.data.jpa.repository.JpaRepository;
import studio.dboo.reserve.api.room.entity.Room;

import javax.transaction.Transactional;

/**
 * Created by dboo on 2021/11/08
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */

@Transactional
public interface RoomRepository extends JpaRepository<Room, Long> {

}
