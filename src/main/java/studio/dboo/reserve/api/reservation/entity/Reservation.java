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
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor
public class Reservation extends TimeEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Room room;

    @OneToMany(mappedBy = "reservation")
    List<Occupancy> occupancies = new ArrayList<>();

    private String whoReserve; // 예약자
    private Boolean isRemainingReceivable; //미수금 잔여 여부
    private String receivable; //미수금액
}
