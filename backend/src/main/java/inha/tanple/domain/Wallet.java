package inha.tanple.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "wallet_id")
    private Long id;

    @OneToOne(mappedBy = "wallet", fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private int balance = 0;

    @OneToMany(mappedBy = "wallet")
    @BatchSize(size = 100)
    private List<CreditHistory> creditHistories = new ArrayList<>();
}
