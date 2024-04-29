package inha.tanple.controller.v1;

import inha.tanple.dto.CreditSummary;
import inha.tanple.dto.response.CreditSummaryResponseDto;
import inha.tanple.service.CreditService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/me")
public class UserController {

    private final CreditService creditService;


    @Operation(summary = "크레딧 요약 정보를 가져옵니다.")
    @GetMapping
    public ResponseEntity<CreditSummaryResponseDto> getCreditSummary() {
        // 현재 인증된 사용자 ID 가져오기 (예: userId = 1L)
        Long userId = 1L;

        CreditSummary creditSummary = creditService.getCreditSummary(userId);

        return ResponseEntity.ok(new CreditSummaryResponseDto(creditSummary));
    }
}