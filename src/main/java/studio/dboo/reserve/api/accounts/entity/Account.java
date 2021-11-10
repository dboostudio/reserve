package studio.dboo.reserve.api.accounts.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import studio.dboo.reserve.api.base.AddressTimeEntity;
import studio.dboo.reserve.api.base.TimeEntity;
import studio.dboo.reserve.api.inn.entity.Inn;

import javax.persistence.*;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
public class Account extends AddressTimeEntity {


    final static String ENTER_USER_ID = "아이디를 입력해 주세요.";
    final static String CHECK_USER_ID = "아이디는 이메일 형식이어야 합니다. (e.g. something@email.com)";
    final static String ENTER_PASSWORD = "비밀번호를 입력해 주세요.";
    final static String PASSWORD_SIZE_NOT_CORRECT = "비밀번호는 8자 이상, 20자 이하로 입력해 주세요.";
    final static String CELLPHONE_FORM_NOT_CORRECT = "010-xxxx-xxxx 의 형식에 맞춰서 입력해주세요.";
    final static String EMAIL_REGEXP = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    @Id
    @GeneratedValue
    private Long id;

    /** Login Info **/
    @Column(unique = true)
    @NotBlank(message = ENTER_USER_ID)
    @Pattern(regexp = EMAIL_REGEXP, message = CHECK_USER_ID)
    private String userId;                //사용자 아이디(이메일)

    @NotBlank(message = ENTER_PASSWORD)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @Size(min = 8, max = 20, message = PASSWORD_SIZE_NOT_CORRECT)
    private String password;                // 비밀번호

    @OneToMany(mappedBy = "account")
    private List<Inn> inns = new ArrayList<>();

    @JsonIgnore
    private String role; //권한 (ADMIN, USER)

    /** Private Info **/
    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = CELLPHONE_FORM_NOT_CORRECT)
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
