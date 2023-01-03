package com.example.tikicloned.domain.converter;


import com.example.tikicloned.domain.enums.UserStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserStatusConverter implements AttributeConverter<UserStatus, String> {
    @Override
    public String convertToDatabaseColumn(UserStatus attribute) {
        return attribute.getValue();
    }

    @Override
    public UserStatus convertToEntityAttribute(String dbData) {
        return UserStatus.of(dbData);
    }
}
