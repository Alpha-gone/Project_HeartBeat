package com.github.heartbeat.rdb.apidata.converter;

import com.github.heartbeat.rdb.apidata.constdata.UserStatus;
import jakarta.persistence.AttributeConverter;

public class StatusConverter implements AttributeConverter<UserStatus, Short> {
    @Override
    public Short convertToDatabaseColumn(UserStatus attribute) {
        return switch (attribute) {
            case GOOD -> 0;
            case CAUTION -> 1;
            case WARING-> 2;
        };
    }

    @Override
    public UserStatus convertToEntityAttribute(Short dbData) {
        return switch (dbData){
            case 0 -> UserStatus.GOOD;
            case 1 -> UserStatus.CAUTION;
            case 2 -> UserStatus.WARING;
            default -> throw new IllegalStateException("Unexpected value: " + dbData);
        };
    }
}
