package com.server.cms.framework.converter;

import com.server.cms.framework.inter.CodeType;
import jakarta.persistence.AttributeConverter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

public class EnumTypeAttributeConverter<T extends CodeType> implements AttributeConverter<T, String> {

    private Class<T> enumType;

    public EnumTypeAttributeConverter() {
        this.enumType = detectAttributeType();
    }

    private Class<T> detectAttributeType() {
        Type type = getClass().getGenericSuperclass();
        if(type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return (Class<T>) parameterizedType.getActualTypeArguments()[0];
        }
        return null;
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        return attribute.getCode();
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        return Arrays.stream(enumType.getEnumConstants())
                .filter(t -> t.is(dbData))
                .findAny()
                .orElse(null);
    }
}