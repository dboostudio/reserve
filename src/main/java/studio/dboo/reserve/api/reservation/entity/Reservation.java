package studio.dboo.reserve.api.reservation.entity;

import studio.dboo.reserve.api.base.TimeEntity;

/**
 * Created by dboo on 2021/11/08
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */
import lombok.*;
import studio.dboo.reserve.api.inn.entity.Inn;
import studio.dboo.reserve.api.room.entity.Room;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Reservation extends TimeEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Room room; // 예약 룸

    private String whoReserve; // 예약자
    private LocalDate startAt; // 예약일-시작
    private LocalDate endAt; // 예약일-끝

}
