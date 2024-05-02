package inha.tanple.domain;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import groovyjarjarantlr4.v4.runtime.misc.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product extends BaseEntity {

    @Id
    private Long productBarcode;

    @NotNull
    private String name;

    @NotNull
    private String company;

    @NotNull
    private String productName;


    @NotNull
    private int price;

    private String taxNumber;

    @NotNull
    private int taxIncludedPrice;

    @NotNull
    private String category;

    @NotNull
    private float taxRate;

    // 비즈니스 로직: 적립 시작-종료 일자입니다.
    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    // 엔티티 관리 로직: 엔티티 갱신 일자입니다.
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}