package inha.tanple.controller.v1;

import inha.tanple.dto.CreditSummary;
import inha.tanple.dto.response.CreditSummaryResponseDto;
import inha.tanple.service.CreditHistoryService;
import inha.tanple.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "사용자 정보", description = "사용자 정보를 관리하기 위한 API 엔드포인트입니다.")
public class MemberController {

    private final CreditHistoryService transactionService;
    private final MemberService memberService;

    @Operation(summary = "개인 요약 정보 조회", description = "개인 요약 정보를 가져옵니다.")
    @GetMapping("/v1/summary")
    public CreditSummaryResponseDto getMemberSummary() {
        // TODO: 현재 인증된 사용자 ID 가져오기 (예: userId = 1000L)
        Long userId = 1000L;

        return new CreditSummaryResponseDto(new CreditSummary());
    }
}