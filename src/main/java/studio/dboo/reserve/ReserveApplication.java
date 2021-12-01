package studio.dboo.reserve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by dboo on 2021/11/04
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */
@SpringBootApplication
@EnableJpaAuditing
public class ReserveApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReserveApplication.class, args);
    }

}
