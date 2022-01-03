package studio.dboo.reserve.module.base;

/**
 * Created by dboo on 2021/11/08
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Address {

    @Id @GeneratedValue
    private Long id;

    private String country; // 국가/지역
    private String state; // 시/도
    private String city; // 시/군/구
    private String address; // 도로명 주소
    private String addressDetail;// 상세 주소
    private String zipCode;// 우편번호

}
