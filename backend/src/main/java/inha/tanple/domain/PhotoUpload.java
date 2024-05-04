package inha.tanple.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PhotoUpload extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_upload_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_barcode", nullable = false)
    private Product product;

    @Column
    private String photoUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PhotoUploadStatus photoUploadStatus; // ["PENDING", "SUCCESS", "FAIL"],
    // PENDING -> SUCCESS => CreditHistory 로 적립
}
