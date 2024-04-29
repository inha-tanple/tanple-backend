package inha.tanple.controller.v1;

import inha.tanple.domain.Submission;
import inha.tanple.service.SubmissionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/submissions")
public class SubmissionController {
    private final SubmissionService submissionService;


    @Operation(summary = "사진을 서버에 제출합니다.")
    @PostMapping
    public ResponseEntity<Void> submitProduct(@RequestBody Submission submission) {
        submissionService.submitProduct(submission);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "제출 리스트를 가져옵니다.")
    @GetMapping
    public ResponseEntity<List<Submission>> getSubmissions() {
        // 현재 인증된 사용자 ID 가져오기 (예: userId = 1L)
        Long userId = 1L;

        List<Submission> submissions = submissionService.getSubmissionsByUserId(userId);
        return ResponseEntity.ok(submissions);
    }


}
