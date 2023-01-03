package com.example.tikicloned.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
@Getter
@AllArgsConstructor
public enum TokenType {
    BEARER_TOKEN("Bearer");

    private final String value;

    private static final Map<String, TokenType> objMap = new HashMap<>();


    static {
        for (TokenType obj : TokenType.values()) {
            objMap.put(obj.value, obj);
        }
    }

    public static TokenType of(String s) {
        return objMap.get(s);
    }
}
