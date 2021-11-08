package studio.dboo.reserve.api.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
/**
 * Created by dboo on 2021/11/04
 * Blog : http://dboostudio.github.io
 * Github : http://github.com/dboostudio
 * Gitlab : http://dboostudio.synology.me:30000
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdAt;

    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    // TODO - CreatedBy, LastModifiedBy 추가 with
    // https://ziponia.github.io/2019/05/13/@CreatedBy.html
}
