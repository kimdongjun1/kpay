package kr.payple.pay.auth.controller;

import kr.payple.pay.auth.dto.TokenDto;
import kr.payple.pay.auth.service.OauthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import kr.payple.pay.auth.dto.OauthReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/oauth", produces = "application/json; charset=utf8")
public class OauthController {

    private final OauthService oauthService;
    private static final Logger log = LoggerFactory.getLogger(OauthController.class);

    @PostMapping("/test")
    public String postTest(){
        return "success";
    }

    @PostMapping(value = "/2.0/token")
    @ResponseBody
    public List<TokenDto> getServiceInfo(@RequestBody Map<String, String> param) throws Exception{

        List<TokenDto> getTokenData = oauthService.getServiceList(param);

        return getTokenData;

    }


}
