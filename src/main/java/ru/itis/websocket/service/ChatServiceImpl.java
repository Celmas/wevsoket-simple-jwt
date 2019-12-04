package ru.itis.websocket.service;

import org.springframework.stereotype.Service;
import ru.itis.websocket.dto.MessageDto;
import ru.itis.websocket.model.Message;
import ru.itis.websocket.model.User;
import ru.itis.websocket.repository.MessageRepository;
import ru.itis.websocket.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    private MessageRepository repository;
    private UserRepository userRepository;

    public ChatServiceImpl(MessageRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public List<MessageDto> getHistory() {
        return repository.findAllByOrderByTimestampAsc().stream().map(message -> MessageDto.builder()
                .author(message.getUser().getUsername())
                .message(message.getMessage())
                .timestamp(message.getTimestamp())
                .build()).collect(Collectors.toList());
    }

    @Override
    public Optional<Message> save(MessageDto messageDto) {
        User user = userRepository.getByUsername(messageDto.getAuthor()).get();
        Message message = repository.save(Message.builder()
                .message(messageDto.getMessage())
                .user(user)
                .timestamp(messageDto.getTimestamp())
                .build());
        return Optional.of(message);
    }
}
