package inha.tanple.controller.v1;

import inha.tanple.dto.request.GoogleAuthRequestDto;
import inha.tanple.dto.request.PhoneAuthRequestDto;
import inha.tanple.dto.response.GoogleAuthResponseDto;
import inha.tanple.dto.response.PhoneAuthResponseDto;
import inha.tanple.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "로그인", description = "로그인을 위한 API 엔드포인트입니다.")
public class LoginController {

    private final MemberService memberService;

    @Operation(summary = "핸드폰 로그인", description = "핸드폰 번호를 이용해 로그인을 요청합니다.")
    @PostMapping("/v1/login")
    public PhoneAuthResponseDto authenticateWithPhone(@RequestBody PhoneAuthRequestDto request) {

        String accessToken = memberService.authenticateWithPhone(request.getPhoneNumber(), request.getBirthDate());
        PhoneAuthResponseDto response = new PhoneAuthResponseDto(accessToken);
        return response;
    }

    @Operation(summary = "구글 로그인", description = "구글 계정을 이용해 로그인을 요청합니다.")
    @PostMapping("/v1/login/google")
    public GoogleAuthResponseDto authenticateWithGoogle(@RequestBody GoogleAuthRequestDto request) {

        String accessToken = memberService.authenticateWithGoogle(request.getIdToken());
        GoogleAuthResponseDto response = new GoogleAuthResponseDto(accessToken);
        return response;

    }

}
