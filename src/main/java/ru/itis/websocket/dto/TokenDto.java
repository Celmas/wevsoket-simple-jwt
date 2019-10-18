package ru.itis.websocket.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDto {
    private String tokenValue;

    public static TokenDto from(String value) {
        return TokenDto.builder().tokenValue(value).build();
    }
}
