package inha.tanple.service;

import inha.tanple.domain.CreditHistory;
import inha.tanple.domain.Member;
import inha.tanple.domain.Wallet;
import inha.tanple.dto.CreditSummary;
import inha.tanple.exception.ResourceNotFoundException;
import inha.tanple.repository.CreditHistoryRepository;
import inha.tanple.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditHistoryService {

    private final CreditHistoryRepository creditHistoryRepository;
    private final MemberRepository memberRepository;

    public CreditSummary getCreditSummary(Long userId) {
        // TODO:
        return new CreditSummary();
    }

    public List<CreditHistory> getCreditHistories(Long memberId) {
        // TODO:

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        List<CreditHistory> creditHistories = creditHistoryRepository.findByMemberId(memberId);
        return creditHistories;
    }

    public List<CreditHistory> getPendingCreditHistories(Long memberId) {
        // TODO:

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        Wallet wallet = member.getWallet();

        List<CreditHistory> creditHistories = creditHistoryRepository.findByMemberId(memberId);

        for (CreditHistory creditHistory : creditHistories) {

        }

        // TODO::
        return creditHistories;
    }

}