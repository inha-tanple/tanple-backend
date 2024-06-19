package inha.tanple.service;

import inha.tanple.domain.Wallet;
import inha.tanple.dto.TransactionRequestDto;
import inha.tanple.exception.InsufficientBalanceException;
import inha.tanple.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final WalletRepository walletRepository;

    @Transactional
    public void exchangeCredits(Long selfId, TransactionRequestDto requestDto) {
        // 본인과 거래할 멤버의 월렛 정보 가져오기
        Wallet selfWallet = walletRepository.findByMemberId(selfId)
                .orElseThrow(() -> new RuntimeException("Self wallet not found for memberId: " + selfId));

        Wallet memberWallet = walletRepository.findByMemberId(requestDto.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member wallet not found for memberId: " + requestDto.getMemberId()));

        // 충분한 잔액이 있는지 확인
        if (selfWallet.getBalance() < requestDto.getAmount()) {
            throw new InsufficientBalanceException("Insufficient balance in your wallet");
        }

        // 크레딧 교환
        selfWallet.debit(requestDto.getAmount());
        memberWallet.credit(requestDto.getAmount());

        // 월렛 업데이트
        walletRepository.save(selfWallet);
        walletRepository.save(memberWallet);
    }
}
