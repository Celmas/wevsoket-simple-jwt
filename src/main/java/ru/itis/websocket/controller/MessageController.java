package ru.itis.websocket.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.websocket.dto.MessageDto;
import ru.itis.websocket.service.ChatService;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class MessageController {

    private ChatService chatService;
    private SimpMessagingTemplate template;

    public MessageController(ChatService chatService, SimpMessagingTemplate template) {
        this.chatService = chatService;
        this.template = template;
    }

    @MessageMapping("/room/{room}")
    @SendTo("/topic/room/{room}")
    public MessageDto getMessage(@DestinationVariable String room, @Payload MessageDto message) {
        message.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        message.setTimestamp(Long.toString(System.currentTimeMillis()));
        message.setRoom(room);
        chatService.save(message);
        return message;
    }

    @RequestMapping("/history/{room}")
    public List<MessageDto> getChatHistory(@PathVariable String room) {
        return chatService.getHistory(room);
    }
}
