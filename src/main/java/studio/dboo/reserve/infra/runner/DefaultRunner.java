package studio.dboo.reserve.infra.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import studio.dboo.reserve.api.accounts.AccountRepository;
import studio.dboo.reserve.api.accounts.entity.Account;
import studio.dboo.reserve.api.inn.InnRepository;
import studio.dboo.reserve.api.inn.entity.Inn;
import studio.dboo.reserve.api.inn.entity.InnType;
import studio.dboo.reserve.api.reservation.ReservationRepository;
import studio.dboo.reserve.api.room.RoomRepository;
import studio.dboo.reserve.api.room.entity.Room;

import java.util.ArrayList;
import java.util.List;

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

        Account newAccount2 = Account.builder()
                .userId("1@gmail.com")
                .password(passwordEncoder.encode("1"))
                .build();

        accountRepository.save(newAccount);
        accountRepository.save(newAccount2);

        Inn newInn = Inn.builder()
                .account(newAccount)
                .innName("게하")
                .innType(InnType.GUESTHOUSE)
                .build();
        Inn newInn1 = Inn.builder()
                .account(newAccount)
                .innName("hotel")
                .innType(InnType.HOTEL)
                .build();
        Inn newInn2 = Inn.builder()
                .account(newAccount)
                .innName("motel")
                .innType(InnType.MOTEL)
                .build();
        Inn newInn3 = Inn.builder()
                .account(newAccount)
                .innName("pension")
                .innType(InnType.PENSION)
                .build();

        innRepository.save(newInn);
        innRepository.save(newInn1);
        innRepository.save(newInn2);
        innRepository.save(newInn3);

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
