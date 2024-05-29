package inha.tanple.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseTimeEntity {

    @Id
    private Long productBarcode; // 바코드

    @Column(nullable = false)
    private String company; // 제조사/유통사

    @Column(nullable = false)
    private String productName; // 제품명

    @Column(nullable = false)
    private int price; // 판매가

    @Column(length = 20)
    private String businessRegistrationNumber; // 사업자등록번호

    @Column(nullable = false)
    private int earnedCredit; // 적립 포인트 -> 적립 크레딧

    @Column(length = 20, nullable = false)
    private String certificationCategory; // 인증구분

    @Column(nullable = false)
    private float earningRate; // 적립율

    @Column(nullable = false)
    private String registerStartDate; // 시작일

    @Column(nullable = false)
    private String registerEndDate; // 마감일
}