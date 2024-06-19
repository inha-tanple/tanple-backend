package inha.tanple.repository;

import inha.tanple.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // 추가적인 메서드가 필요하지 않은 경우


}
