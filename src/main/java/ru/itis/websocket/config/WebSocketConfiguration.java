package ru.itis.websocket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import ru.itis.websocket.handler.AuthHandshakeHandler;
import ru.itis.websocket.handler.MessagesWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    private final MessagesWebSocketHandler messagesWebSocketHandler;

    private final AuthHandshakeHandler authHandshakeHandler;

    public WebSocketConfiguration(MessagesWebSocketHandler messagesWebSocketHandler, AuthHandshakeHandler authHandshakeHandler) {
        this.messagesWebSocketHandler = messagesWebSocketHandler;
        this.authHandshakeHandler = authHandshakeHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(messagesWebSocketHandler, "/chat").setAllowedOrigins("*")
                .setHandshakeHandler(authHandshakeHandler);
    }
}
