package inha.tanple.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Column(nullable = false)
    private String name;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastModifiedDate;

//interface 용 데이터를 작성했습니다.
    @Id // 기존의 ID 대신 Barcode로 대체하였습니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long barcode;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private int price;

    @Column(nullable = true)
    private String taxNumber;

    @Column(nullable = false)
    private int taxIncludedPrice;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private float taxRate;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    // startDate와 endDate의 할당을 분리하였습니다.
    protected void setStartDate(Date startDate) {
        startDate = new Date();
    }

    protected void setEndDate(Date endDate) {
        endDate = new Date();
    }

}