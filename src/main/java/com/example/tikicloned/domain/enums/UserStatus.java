package com.example.tikicloned.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum UserStatus {
    ACTIVE("ACTIVE"),
    PENDING("PENDING"),
    LOCKED("LOCKED"),
    DEACTIVATED("DEACTIVATED");

    private final String value;

    private static final Map<String, UserStatus> objMap = new HashMap<>();


    static {
        for (UserStatus obj : UserStatus.values()) {
            objMap.put(obj.value, obj);
        }
    }

    public static UserStatus of(String s) {
        return objMap.get(s);
    }

}
