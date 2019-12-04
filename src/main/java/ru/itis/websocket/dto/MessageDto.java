package ru.itis.websocket.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@Builder
@ToString
public class MessageDto {
    private String author;
    private String message;
    private Timestamp timestamp;

}
