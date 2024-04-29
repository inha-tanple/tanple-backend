package inha.tanple.repository;

import inha.tanple.domain.CreditTransaction;
import inha.tanple.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CreditTransactionRepository extends JpaRepository<CreditTransaction, Long> {

    List<CreditTransaction> findByUser(User user);

    @Query("SELECT SUM(ct.amount) FROM CreditTransaction ct WHERE ct.user.id = :userId")
    int getTotalCredits(@Param("userId") Long userId);

    @Query("SELECT SUM(ct.amount) FROM CreditTransaction ct WHERE ct.user.id = :userId AND ct.status = 'PENDING'")
    int getPendingCredits(@Param("userId") Long userId);


}