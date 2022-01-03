package studio.dboo.reserve.infra.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import studio.dboo.reserve.module.accounts.AccountRepository;
import studio.dboo.reserve.module.accounts.entity.Account;
import studio.dboo.reserve.module.inn.InnRepository;
import studio.dboo.reserve.module.inn.entity.Inn;
import studio.dboo.reserve.module.reservation.ReservationRepository;
import studio.dboo.reserve.module.room.RoomRepository;
import studio.dboo.reserve.module.room.entity.Room;

@Component
@RequiredArgsConstructor
public class DefaultRunner implements ApplicationRunner {

    private final AccountRepository accountRepository;
    private final InnRepository innRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account newAccount = Account.builder()
                .userId("dboo.studio@gmail.com")
                .password(passwordEncoder.encode("eoghks1@!2_"))
                .lastname("boo")
                .firstname("daehwan")
                .gender("male")
                .build();

        accountRepository.save(newAccount);

        Inn newInn = Inn.builder()
                .account(newAccount)
                .innName("dbooWorld")
//                .address(Address.builder()
//                        .country("korea")
//                        .city("제주시")
//                        .state("제주특별자치도")
//                        .address("독짓골2길 25")
//                        .addressDetail("마동 302호")
//                        .zipCode("63112")
//                        .build())
                .build();

        innRepository.save(newInn);

        Room newRoom1 = Room.builder()
                .inn(newInn)
                .roomName("101호")
                .numberOfBedroom(1)
                .quota(2)
                .comment("101호 입니다.")
                .build();

        Room newRoom2 = Room.builder()
                .inn(newInn)
                .roomName("201호")
                .numberOfBedroom(2)
                .quota(4)
                .comment("201호 입니다.")
                .build();

        Room newRoom3 = Room.builder()
                .inn(newInn)
                .roomName("301호")
                .numberOfBedroom(3)
                .quota(6)
                .comment("301호 입니다.")
                .build();

        roomRepository.save(newRoom1);
        roomRepository.save(newRoom2);
        roomRepository.save(newRoom3);
    }
}
