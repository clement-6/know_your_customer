package upload.document.know_your_customer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Version
    @Column(name = "VERSION" , nullable = false)
    private Long version;

    @Column(name = "CREATED", updatable = false)
    @CreatedDate
    private LocalDateTime createdDate;


    @Column(name = "LAST_MODIFIED")
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
