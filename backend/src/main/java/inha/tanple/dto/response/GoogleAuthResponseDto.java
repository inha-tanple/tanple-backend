package inha.tanple.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class GoogleAuthResponseDto {
    private final String accessToken;
    private final String tokenType = "Bearer";
}
