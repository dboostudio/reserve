package studio.dboo.reserve.api.accounts.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import studio.dboo.reserve.view.base.AddressTimeEntity;
import studio.dboo.reserve.api.inn.entity.Inn;

import javax.persistence.*;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by dboo on 2021/11/04
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */
@Entity
@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Account extends AddressTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Login Info **/
    @Column(unique = true)
    private String userId;                //사용자 아이디(이메일)

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @Size(min = 8, max = 20, message = PASSWORD_SIZE_NOT_CORRECT)
//    패스워드 유효성검사는 서비스단에서 따로 처리해야 한다.
    private String password;                // 비밀번호

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Inn> inns;

    @JsonIgnore
    private String role; //권한 (ADMIN, USER)

    /** Private Info **/
    private String cellPhone;               // 핸드폰번호
    private String lastname;                // 성
    private String firstname;               // 이름
    private LocalDate birth;                   // 생년월일
    private String gender;                     // 성별

    /** Tier, Point **/
    private Integer tier;                   // 등급 : 1~5 blonze, silver, gold, platinum, diamond
    private Long point;                     // 포인트

    /** Encrypt Password */
    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }
}
