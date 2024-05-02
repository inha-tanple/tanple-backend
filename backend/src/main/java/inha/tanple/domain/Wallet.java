package inha.tanple.domain;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
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
    @GeneratedValue
    @Column(name = "wallet_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @NotNull
    private int balance = 0;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.PERSIST)
    @BatchSize(size = 100)
    private List<CreditHistory> creditHistories = new ArrayList<>();
}
