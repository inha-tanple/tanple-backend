package inha.tanple.repository;

import inha.tanple.domain.CreditHistory;
import inha.tanple.domain.Member;
import inha.tanple.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CreditHistoryRepository extends JpaRepository<CreditHistory, Long> {

    List<CreditHistory> findByWallet(Wallet wallet);

    @Query("SELECT ct FROM CreditHistory ct WHERE ct.wallet.member = :memberId")
    List<CreditHistory> findByMemberId(@Param("memberId") Long memberId);
}