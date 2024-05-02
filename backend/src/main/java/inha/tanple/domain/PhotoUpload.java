package inha.tanple.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PhotoUpload extends BaseEntity {
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

    private String photoUrl;

    @Enumerated(EnumType.STRING)
    private PhotoUploadStatus photoUploadStatus; // ["PENDING", "SUCCESS", "FAIL"]
}
