package studio.dboo.reserve.api.accounts.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import studio.dboo.reserve.api.inn.entity.Inn;
import studio.dboo.reserve.view.base.AddressTimeEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class AccountDTO extends AddressTimeEntity {


    final static String ENTER_USER_ID = "아이디를 입력해 주세요.";
    final static String CHECK_USER_ID = "아이디는 이메일 형식이어야 합니다. (e.g. something@email.com)";
    final static String ENTER_PASSWORD = "비밀번호를 입력해 주세요.";
    final static String PASSWORD_SIZE_NOT_CORRECT = "비밀번호는 8자 이상, 20자 이하로 입력해 주세요.";
    final static String CELLPHONE_FORM_NOT_CORRECT = "010-xxxx-xxxx 의 형식에 맞춰서 입력해주세요.";
    final static String EMAIL_REGEXP = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    private Long id;

    /** Login Info **/
    @NotBlank(message = ENTER_USER_ID)
    @Pattern(regexp = EMAIL_REGEXP, message = CHECK_USER_ID)
    private String userId;                //사용자 아이디(이메일)

    @NotBlank(message = ENTER_PASSWORD)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @Size(min = 8, max = 20, message = PASSWORD_SIZE_NOT_CORRECT)
//    패스워드 유효성검사는 서비스단에서 따로 처리해야 한다.
    private String password;                // 비밀번호

    private List<Inn> inns;

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
}
