package com.example.tikicloned.domain.dto.request;

import com.example.tikicloned.domain.converter.GrantTypeConverter;
import com.example.tikicloned.domain.converter.UserProviderConverter;
import com.example.tikicloned.domain.enums.GrantType;
import com.example.tikicloned.domain.enums.UserProvider;
import lombok.*;

import javax.persistence.Convert;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest implements Serializable {
    private String username;
    private String password;
    @Convert(converter = GrantTypeConverter.class)
    private GrantType grantType;
    @Convert(converter = UserProviderConverter.class)
    private UserProvider provider;
}
