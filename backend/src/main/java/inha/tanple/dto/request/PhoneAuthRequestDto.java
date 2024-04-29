package inha.tanple.dto.request;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PhoneAuthRequestDto {
    private String phoneNumber;
    private LocalDate birthDate;
}
