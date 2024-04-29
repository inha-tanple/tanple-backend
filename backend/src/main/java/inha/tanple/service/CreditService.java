package inha.tanple.service;

import inha.tanple.domain.CreditTransaction;
import inha.tanple.domain.User;
import inha.tanple.dto.CreditSummary;
import inha.tanple.exception.ResourceNotFoundException;
import inha.tanple.repository.CreditTransactionRepository;
import inha.tanple.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final CreditTransactionRepository creditTransactionRepository;
    private final UserRepository userRepository;


    public CreditSummary getCreditSummary(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        int totalCredits = creditTransactionRepository.getTotalCredits(userId);
        int pendingCredits = creditTransactionRepository.getPendingCredits(userId);

        return new CreditSummary(totalCredits, pendingCredits);
    }

    public List<CreditTransaction> getCredits(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<CreditTransaction> credits = creditTransactionRepository.findByUser(user);
        return credits;
    }


}