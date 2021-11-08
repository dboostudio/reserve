package studio.dboo.reserve.api.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import studio.dboo.reserve.api.reservation.entity.Reservation;

import javax.transaction.Transactional;

/**
 * Created by dboo on 2021/11/08
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */

@Transactional
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
