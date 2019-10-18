package ru.itis.websocket.service;

import ru.itis.websocket.dto.UserDto;
import ru.itis.websocket.model.User;

public interface UserService {
    String login(String username, String password);
    String register(User user);
}
