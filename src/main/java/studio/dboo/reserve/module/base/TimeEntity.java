package studio.dboo.reserve.module.base;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@Builder
@Getter @Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class TimeEntity {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    // TODO - CreatedBy, LastModifiedBy 추가 with
    // https://ziponia.github.io/2019/05/13/@CreatedBy.html
}
