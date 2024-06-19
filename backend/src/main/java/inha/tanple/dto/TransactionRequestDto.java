package inha.tanple.dto;

import inha.tanple.domain.Wallet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequestDto {

    private Long memberId;
    private Wallet fromWallet;
    private Wallet toWallet;
    private int amount;
    private String description;

}
