package com.example.tikicloned.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum GrantType {
    PASSWORD("password");

    private final String value;

    private static final Map<String, GrantType> objMap = new HashMap<>();


    static {
        for (GrantType obj : GrantType.values()) {
            objMap.put(obj.value, obj);
        }
    }

    public static GrantType of(String s) {
        return objMap.get(s);
    }
}
