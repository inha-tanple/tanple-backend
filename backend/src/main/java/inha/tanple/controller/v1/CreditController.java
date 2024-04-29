package inha.tanple.controller.v1;

import inha.tanple.domain.CreditTransaction;
import inha.tanple.dto.CreditSummary;
import inha.tanple.service.CreditService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/me/credits")
public class CreditController {

    private final CreditService creditService;

    @Operation(summary = "개인 크레딧 정보(크레딧, 거래내역, 처리중 내역)를 가져옵니다.")
    @GetMapping
    public ResponseEntity<List<CreditTransaction>> getCredits(
            @Nullable @RequestParam String category,
            @Nullable @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @Nullable @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        // 기간 내 크레딧 정보 가져오기 로직 구현
        // 현재 인증된 사용자 ID 가져오기 (예: userId = 1L)
        Long userId = 1L;

        List<CreditTransaction> credits = creditService.getCredits(userId);

        return ResponseEntity.ok(credits);
    }

    @Operation(summary = "개인 크레딧 정보를 캘린더 형식으로 가져옵니다.")
    @GetMapping("/calendar")
    public ResponseEntity<List<CreditTransaction>> getCreditsInCalendar() {
        // 기간 내 크레딧 정보 가져오기 로직 구현
        // 현재 인증된 사용자 ID 가져오기 (예: userId = 1L)
        Long userId = 1L;

        List<CreditTransaction> credits = creditService.getCredits(userId);

        return ResponseEntity.ok(credits);
    }


}