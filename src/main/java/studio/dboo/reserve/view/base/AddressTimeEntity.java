package studio.dboo.reserve.view.base;

/**
 * Created by dboo on 2021/11/08
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AddressTimeEntity extends TimeEntity {

    private String country; // 국가/지역
    private String state; // 시/도
    private String city; // 시/군/구
    private String address; // 도로명 주소
    private String addressDetail;// 상세 주소
    private String zipCode;// 우편번호

}
