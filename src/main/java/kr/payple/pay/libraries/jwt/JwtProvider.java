package kr.payple.pay.libraries.jwt;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

@Component
public class JwtProvider {

    private static final String secretKey = Base64.getEncoder().encodeToString("bmVCR0J5b2xKbitFd1h1R3hKcGU2VE1VSGh1ZTJWZFFmQUQwVTJHNzFVS3g1QzluYUMzTkcrY2lKd0FGbHJ0YQ".getBytes());

    public String createToken(Claims claims) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(createExpireDateForOneYear())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return claimsJws.getBody().getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    private static Date createExpireDateForOneYear() {
        // 토큰 만료시간은 10분으로 설정
        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, 600);
        return c.getTime();
    }

    public Claims getInformation(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public Claims getClaimsFormToken(String token) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey)).parseClaimsJws(token).getBody();
    }

}
