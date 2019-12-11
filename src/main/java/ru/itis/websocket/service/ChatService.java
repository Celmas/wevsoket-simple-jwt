package ru.itis.websocket.service;

import ru.itis.websocket.dto.MessageDto;
import ru.itis.websocket.model.Message;

import java.util.List;
import java.util.Optional;

public interface ChatService {
    List<MessageDto> getHistory(String room);
    Optional<Message> save(MessageDto messageDto);
}
