package com.example.tikicloned.domain.converter;


import com.example.tikicloned.domain.enums.UserProvider;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserProviderConverter implements AttributeConverter<UserProvider, String> {
    @Override
    public String convertToDatabaseColumn(UserProvider attribute) {
        return attribute.getValue();
    }

    @Override
    public UserProvider convertToEntityAttribute(String dbData) {
        return UserProvider.of(dbData);
    }
}
