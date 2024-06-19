package inha.tanple.controller.v1;

import inha.tanple.dto.TransactionRequestDto;
import inha.tanple.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "거래", description = "월렛 간의 크레딧 이체를 관리합니다.")
public class TransactionController {
    // 탄소배출권을 유저로 생각해서 따로 탄소배출권을 만들지 말고 교환하는 방식으로 어마운트 설정
    private final TransactionService transactionService;

    @Operation(summary = "크레딧 교환", description = "다른 멤버와 크레딧을 교환합니다.")
    @PostMapping("/v1/transactions/exchange")
    public void exchangeCredits(@RequestBody TransactionRequestDto requestDto) {
        transactionService.exchangeCredits(1000L /* 본인의 userId */, requestDto);
    }
}
