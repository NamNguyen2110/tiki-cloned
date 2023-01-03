package com.example.tikicloned.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum UserProvider {
    GOOGLE("GOOGLE"), FACEBOOK("FACEBOOK"), SYSTEM("SYSTEM");

    private final String value;

    private static final Map<String, UserProvider> objMap = new HashMap<>();


    static {
        for (UserProvider obj : UserProvider.values()) {
            objMap.put(obj.value, obj);
        }
    }

    public static UserProvider of(String s) {
        return objMap.get(s);
    }

}
