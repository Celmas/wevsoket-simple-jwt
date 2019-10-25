package ru.itis.websocket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.websocket.dto.TokenDto;
import ru.itis.websocket.dto.UserDto;
import ru.itis.websocket.model.User;
import ru.itis.websocket.service.UserService;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @PostMapping("login")
    public ResponseEntity<TokenDto> login(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(TokenDto.from(userService.login(userDto.getLogin(), userDto.getPassword())));
    }

    @CrossOrigin
    @PostMapping("register")
    public ResponseEntity<TokenDto> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(TokenDto.from(userService.register(User
                .builder()
                .username(userDto.getLogin())
                .password(userDto.getPassword())
                .build())));
    }
}
