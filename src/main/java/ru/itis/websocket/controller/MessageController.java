package ru.itis.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.websocket.dto.MessageDto;
import ru.itis.websocket.jwt.JwtHelper;
import ru.itis.websocket.model.Message;
import ru.itis.websocket.model.User;
import ru.itis.websocket.repository.MessageRepository;
import ru.itis.websocket.repository.UserRepository;
import ru.itis.websocket.service.ChatService;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MessageController {

    private ChatService chatService;


    public MessageController(JwtHelper jwtHelper, ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/chat")
    public MessageDto getMessage(@Payload MessageDto message) {
        message.setTimestamp(new Timestamp(System.currentTimeMillis()));
        chatService.save(message);
        return message;
    }

    @RequestMapping("/history")
    public List<MessageDto> getChatHistory() {
        return chatService.getHistory();
    }
}
