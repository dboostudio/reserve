package studio.dboo.reserve.module.reservation.entity;

/**
 * Created by dboo on 2021/11/08
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */
import lombok.*;
        import studio.dboo.reserve.module.room.entity.Room;

import javax.persistence.*;
        import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Reservation{

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Room room;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "reservation")
    List<Occupancy> occupancies = new ArrayList<>();

    private String whoReserve; // 예약자
    private Boolean isRemainingReceivable; //미수금 잔여 여부
    private String receivable; //미수금액
}
