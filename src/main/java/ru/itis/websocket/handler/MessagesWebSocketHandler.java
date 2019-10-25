package ru.itis.websocket.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.itis.websocket.dto.MessageDto;
import ru.itis.websocket.jwt.JwtHelper;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MessagesWebSocketHandler extends TextWebSocketHandler {


    private static Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    private ObjectMapper objectMapper = new ObjectMapper();

    private JwtHelper jwtHelper;

    public MessagesWebSocketHandler(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }


    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        HttpHeaders headers = session.getHandshakeHeaders();
        String messageAsString = (String) message.getPayload();
        System.err.println(messageAsString);
        MessageDto body = objectMapper.readValue(messageAsString, MessageDto.class);
        body.setFrom(jwtHelper.getUsername(body.getFrom()));

        if (body.getText().equals("Hello!")) {
            sessions.put(body.getFrom(), session);
        }

        for (WebSocketSession currentSession : sessions.values()) {
            currentSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(body)));
        }
    }
}