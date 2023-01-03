package com.example.tikicloned.filter;

import com.example.tikicloned.domain.dto.response.LoginResponse;
import com.example.tikicloned.domain.dto.response.TokenResponse;
import com.example.tikicloned.domain.enums.UserProvider;
import io.jsonwebtoken.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenHandler {
    private final Environment env;
    @Value("${app.expiredTime}")
    private Long expiredTime;
    @Value("${app.refreshExpiredTime}")
    private Long refreshExpiredTime;

    public LoginResponse data(String username, UserProvider provider) {
        return LoginResponse.builder()
                .accessToken(generator(Data.build(username, env.getProperty("app.secretKey"), expiredTime)))
                .refreshToken(generator(Data.build(username, env.getProperty("app.refreshSecretKey"), refreshExpiredTime)))
                .expiresIn(LocalDateTime.now().getSecond() + expiredTime)
                .refreshExpiresIn(LocalDateTime.now().getSecond() + refreshExpiredTime)
                .provider(provider)
                .build();
    }

    public Boolean isValid(HttpServletRequest request) {
        String token = getToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        if (Strings.isNotEmpty(token)) {
            try {
                Jwts.parser().setSigningKey(env.getProperty("app.secretKey")).parseClaimsJws(token);
                return Boolean.TRUE;
            } catch (MalformedJwtException ex) {
                log.error("Invalid JWT token");
            } catch (ExpiredJwtException ex) {
                log.error("Expired JWT token");
            } catch (UnsupportedJwtException ex) {
                log.error("Unsupported JWT token");
            } catch (IllegalArgumentException ex) {
                log.error("JWT claims string is empty.");
            }
        } else {
            return Boolean.FALSE;
        }
        return Boolean.FALSE;
    }

    public TokenResponse split(HttpServletRequest request) {
        Claims claims = Jwts.parser()
                .setSigningKey(env.getProperty("app.secretKey"))
                .parseClaimsJws(getToken(request.getHeader(HttpHeaders.AUTHORIZATION)))
                .getBody();
        return TokenResponse.builder()
                .username(claims.getSubject())
                .build();
    }


    private String generator(Data request) {
        return Jwts.builder()
                .setId(request.id)
                .setSubject(request.subject)
                .setIssuedAt(request.issuedAt)
                .setExpiration(new Date(new Date().getTime() + request.expiredTime))
                .signWith(SignatureAlgorithm.HS512, request.secretKey)
                .compact();
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    private static class Data {
        private String id;
        private String subject;
        private Date issuedAt;
        private Long expiredTime;
        private String secretKey;

        private static Data build(String username, String secretKey, Long expiredTime) {
            return Data.builder()
                    .id(UUID.randomUUID().toString())
                    .subject(username)
                    .issuedAt(new Date())
                    .secretKey(secretKey)
                    .expiredTime(expiredTime)
                    .build();
        }
    }

    private String getToken(String header) {
        return Strings.isNotEmpty(header) ? header.substring(7) : Strings.EMPTY;
    }

}
