package kr.payple.pay.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TokenDto {

    private String toKenResult;
    private String message;
    private String tokenCode;
    private String access_token;
    private String token_type;
    private String payCls;
    private String expires_in;

    @Builder
    public TokenDto(String toKenResult, String message, String tokenCode, String access_token, String token_type, String payCls, String expires_in) {
        this.toKenResult = toKenResult;
        this.message = message;
        this.tokenCode = tokenCode;
        this.access_token = access_token;
        this.token_type = token_type;
        this.payCls = payCls;
        this.expires_in = expires_in;
    }

}
