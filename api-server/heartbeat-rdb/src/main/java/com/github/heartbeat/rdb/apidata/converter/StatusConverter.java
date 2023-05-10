package com.github.heartbeat.rdb.apidata.converter;

import com.github.heartbeat.rdb.apidata.constdata.UserStatus;
import jakarta.persistence.AttributeConverter;

public class StatusConverter implements AttributeConverter<UserStatus, Byte> {
    @Override
    public Byte convertToDatabaseColumn(UserStatus attribute) {
        return (byte) attribute.ordinal();
    }

    @Override
    public UserStatus convertToEntityAttribute(Byte dbData) {
        return switch (dbData){
            case 0 -> UserStatus.GOOD;
            case 1 -> UserStatus.CAUTION;
            case 2 -> UserStatus.WARING;
            default -> throw new IllegalStateException("Unexpected value: " + dbData);
        };
    }
}
