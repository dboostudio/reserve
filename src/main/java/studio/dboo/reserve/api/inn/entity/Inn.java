package studio.dboo.reserve.api.inn.entity;

/**
 * Created by dboo on 2021/11/07
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */

import lombok.*;
import studio.dboo.reserve.api.accounts.entity.Account;
import studio.dboo.reserve.api.base.AddressTimeEntity;
import studio.dboo.reserve.api.reservation.entity.Reservation;
import studio.dboo.reserve.api.room.entity.Room;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor
public class Inn extends AddressTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account account; // 소유주

    private String innName; // Inn 이름

    private InnType innType; // Inn 유형

    @OneToMany(mappedBy = "inn")
    private List<Room> rooms = new ArrayList<>(); // 방 리스트

}
