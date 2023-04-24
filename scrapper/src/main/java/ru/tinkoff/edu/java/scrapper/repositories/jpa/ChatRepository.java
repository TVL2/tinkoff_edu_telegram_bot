package ru.tinkoff.edu.java.scrapper.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.entity.jpa.JpaChat;

@Repository
public interface ChatRepository extends JpaRepository<JpaChat, Long> {
}
