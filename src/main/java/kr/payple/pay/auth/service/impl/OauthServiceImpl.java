package kr.payple.pay.auth.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import kr.payple.pay.auth.controller.OauthController;
import kr.payple.pay.auth.dto.OauthReqDto;
import kr.payple.pay.auth.dto.TokenDto;
import kr.payple.pay.auth.entity.PartnerServiceEntity;
import kr.payple.pay.auth.repository.OauthRepository;
import kr.payple.pay.auth.service.OauthService;
import kr.payple.pay.libraries.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OauthServiceImpl implements OauthService {
    private final OauthRepository oauthRepository;
    private final JwtProvider jwtProvider;

    private static final Logger log = LoggerFactory.getLogger(OauthController.class);

    // Entity -> Dto로 변환
    private OauthReqDto convertEntityToDto(PartnerServiceEntity partnerService, String conDate ) {
        return OauthReqDto.builder()
                .service_id(partnerService.getServiceId())
                .service_key(partnerService.getService_key())
                .group_id(partnerService.getPartner_group().getGroup_id())
                .conDate(conDate)
                .build();
    }

    @Override
    public List<TokenDto> getServiceList(Map<String, String> param){
        String service_id = param.get("service_id");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        List<PartnerServiceEntity> psEntity = oauthRepository.findByServiceId(service_id);
        List<OauthReqDto> OauthDtoList = new ArrayList<>();

        Claims claims = Jwts.claims();

        for (PartnerServiceEntity partnerServiceEntity : psEntity) {

            claims.put("service_id", partnerServiceEntity.getServiceId());
            claims.put("group_id", partnerServiceEntity.getPartner_group().getGroup_id());
            claims.put("service_key", partnerServiceEntity.getService_key());
            claims.put("conDate", String.valueOf(timestamp));

            //OauthDtoList.add(this.convertEntityToDto(partnerServiceEntity, String.valueOf(timestamp)));
        }

        String token = jwtProvider.createToken(claims);
        System.out.println(jwtProvider.validateToken(token));
        Claims claims1 = jwtProvider.getClaimsFormToken(token);

        Date expiration = claims1.getExpiration();
        log.info(String.valueOf(expiration));
        List<TokenDto> tokenDtoList = new ArrayList<>();

        final TokenDto tokenDto = TokenDto.builder()
                .toKenResult("T0000")
                .message("Process Success")
                .tokenCode(param.get("code"))
                .access_token(token)
                .token_type("Bearer")
                .payCls("git")
                .expires_in("1000L")
                .build();
        tokenDtoList.add(tokenDto);

        return tokenDtoList;
    }




}
