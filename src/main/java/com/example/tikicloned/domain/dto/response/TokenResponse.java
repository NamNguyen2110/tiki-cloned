package com.example.tikicloned.domain.dto.response;

import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenResponse implements Serializable {
    private String username;
}
