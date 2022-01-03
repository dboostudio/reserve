package studio.dboo.reserve.module.room.entity;

/**
 * Created by dboo on 2021/11/08
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */

import lombok.*;
import studio.dboo.reserve.module.inn.entity.Inn;
import studio.dboo.reserve.module.reservation.entity.Reservation;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Room{

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Inn inn;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;

    private String roomName; // 방 번호 / 방 이름
    private Integer quota; // 방 정원
    private Integer numberOfBedroom;// 침실 수
    private String comment; // 특이사항

}
