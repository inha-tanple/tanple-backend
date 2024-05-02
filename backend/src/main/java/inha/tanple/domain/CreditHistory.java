package inha.tanple.domain;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import groovyjarjarantlr4.v4.runtime.misc.Nullable;
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
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @NotNull
    private int amount;

    @Enumerated(EnumType.STRING)
    private CreditStatus creditStatus; // ["PENDING", "CREDITED", "USED"]

    @Nullable
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "upload_id")
    private PhotoUpload photoUpload;
}