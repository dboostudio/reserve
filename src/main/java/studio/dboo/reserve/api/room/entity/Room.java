package studio.dboo.reserve.api.room.entity;

/**
 * Created by dboo on 2021/11/08
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import studio.dboo.reserve.view.base.TimeEntity;
import studio.dboo.reserve.api.inn.entity.Inn;
import studio.dboo.reserve.api.reservation.entity.Reservation;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Room extends TimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
