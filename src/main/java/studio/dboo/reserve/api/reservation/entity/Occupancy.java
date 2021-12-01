package studio.dboo.reserve.api.reservation.entity;

/**
 * Created by dboo on 2021/11/09
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor
public class Occupancy {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    private Reservation reservation;

    private LocalDate reservedDate;
}
