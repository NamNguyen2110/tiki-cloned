package com.example.tikicloned.domain.dto.response;

import com.example.tikicloned.domain.enums.TokenType;
import com.example.tikicloned.domain.enums.UserProvider;
import com.example.tikicloned.domain.enums.UserStatus;
import lombok.*;

import javax.persistence.Convert;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginResponse implements Serializable {
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;
    private Long refreshExpiresIn;
    @Convert(converter = UserStatus.class)
    private TokenType tokenType;
    @Convert(converter = UserStatus.class)
    private UserProvider provider;
}
