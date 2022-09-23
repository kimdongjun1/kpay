package kr.payple.pay.auth.service;

import kr.payple.pay.auth.dto.OauthReqDto;
import kr.payple.pay.auth.dto.TokenDto;

import java.util.List;
import java.util.Map;

public interface OauthService {

    List<TokenDto> getServiceList(Map<String, String> param);
}
