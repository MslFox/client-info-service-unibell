package com.mslfox.unibell.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public enum ContactType {
    EMAIL("email"),

    PHONE("phone");

    private final String value;

    ContactType(String value) {
        this.value = value;
    }

    public String getName() {
        return this.value;
    }

    @JsonCreator
    public static Optional<ContactType> fromValue(String value) {
        if(value!=null) {
            for (ContactType type : ContactType.values()) {
                if (type.toString().equalsIgnoreCase(value.replaceAll("[ _-]", ""))) {
                    return Optional.of(type);
                }
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown type: " + value);
        } else {
            return Optional.empty();
        }
    }
}
