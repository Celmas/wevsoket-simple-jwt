package ru.itis.websocket.service;

import org.springframework.stereotype.Service;
import ru.itis.websocket.dto.UserDto;
import ru.itis.websocket.jwt.JwtHelper;
import ru.itis.websocket.model.User;
import ru.itis.websocket.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private JwtHelper jwtHelper;

    public UserServiceImpl(UserRepository userRepository, JwtHelper jwtHelper) {
        this.userRepository = userRepository;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public String login(String username, String password) {
        Optional<User> candidate = userRepository.getByUsername(username);
        if (candidate.isPresent()) {
            User user = candidate.get();
            if (password.equals(user.getPassword())) {
                return jwtHelper.createToken(user.getUsername(), user.getId());
            }
        }
        throw new IllegalArgumentException("Login attempt failed");
    }

    @Override
    public String register(User userToSave) {
        if (!userRepository.existsByUsername(userToSave.getUsername())) {
            User user = userRepository.save(userToSave);
            return jwtHelper.createToken(user.getUsername(), user.getId());
        }
        throw new IllegalArgumentException("Register attempt failed");
    }
}
