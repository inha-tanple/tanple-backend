package inha.tanple.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(EntityListeners.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    LocalDateTime createdDate;
}
