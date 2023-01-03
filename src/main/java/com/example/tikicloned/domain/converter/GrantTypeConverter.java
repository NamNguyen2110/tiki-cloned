package com.example.tikicloned.domain.converter;


import com.example.tikicloned.domain.enums.GrantType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GrantTypeConverter implements AttributeConverter<GrantType, String> {
    @Override
    public String convertToDatabaseColumn(GrantType attribute) {
        return attribute.getValue();
    }

    @Override
    public GrantType convertToEntityAttribute(String dbData) {
        return GrantType.of(dbData);
    }
}
