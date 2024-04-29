package inha.tanple.controller.v1;

import inha.tanple.dto.request.GoogleAuthRequestDto;
import inha.tanple.dto.request.PhoneAuthRequestDto;
import inha.tanple.dto.response.GoogleAuthResponseDto;
import inha.tanple.dto.response.PhoneAuthResponseDto;
import inha.tanple.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;


    @Operation(summary = "구글 계정을 이용해 로그인을 요청합니다.")
    @PostMapping("/google")
    public ResponseEntity<GoogleAuthResponseDto> authenticateWithGoogle(@RequestBody GoogleAuthRequestDto request) {

        String accessToken = authService.authenticateWithGoogle(request.getIdToken());
        GoogleAuthResponseDto response = new GoogleAuthResponseDto(accessToken);
        return ResponseEntity.ok(response);

    }

    @Operation(summary = "핸드폰 번호를 이용해 로그인을 요청합니다.")
    @PostMapping("/phone")
    public ResponseEntity<PhoneAuthResponseDto> authenticateWithPhone(@RequestBody PhoneAuthRequestDto request) {
        String accessToken = authService.authenticateWithPhone(request.getPhoneNumber(), request.getBirthDate());
        PhoneAuthResponseDto response = new PhoneAuthResponseDto(accessToken);
        return ResponseEntity.ok(response);

    }
}
