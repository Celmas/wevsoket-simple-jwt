package ru.itis.websocket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.websocket.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getByUsername(String username);
    boolean existsByUsername(String username);
}
