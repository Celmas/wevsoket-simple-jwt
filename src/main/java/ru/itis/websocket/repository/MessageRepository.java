package ru.itis.websocket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.websocket.model.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByOrderByTimestampAsc();
}
