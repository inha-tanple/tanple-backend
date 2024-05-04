package inha.tanple.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CreditHistory extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "credit_history_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    @Column(nullable = false)
    private int credit;

    @Column(nullable = false)
    private int balance;

    // enum CreditType = ["CREDITED", "USED"] 은 credit 부호로 판단, Dto 구성 예정

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CreditMethod creditMethod; // ["PURCHASE", "EXCHANGE", "DONATION"]

    @Column(length = 100)
    private String detail; // 소금 1kg, PURCHASE, EXCHANGE, 세제 1L, ...

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_upload_id", nullable = false)
    private PhotoUpload photoUpload; // 사진 업로드 일자, productBarcode 를 가져와 Dto 구성 예정
}
