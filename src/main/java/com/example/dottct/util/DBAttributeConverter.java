package com.example.dottct.util;

import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;

public class DBAttributeConverter implements AttributeConverter<String, String> {

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(String attribute) {
        return AESUtil.encode(attribute);
    }

    @SneakyThrows
    @Override
    public String convertToEntityAttribute(String dbData) {
        return AESUtil.decode(dbData);
    }

}