package studio.dboo.reserve.module.inn.entity;

/**
 * Created by dboo on 2021/11/07
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */

import lombok.*;
import studio.dboo.reserve.module.accounts.entity.Account;
import studio.dboo.reserve.module.room.entity.Room;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Inn{

    @Id @GeneratedValue
    private Long id;

//    @OneToOne(mappedBy = "inn")
//    private Address address;

    @ManyToOne
    private Account account; // 소유주

    private String innName; // Inn 이름

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "inn")
    private List<Room> rooms = new ArrayList<>(); // 방 리스트

}
