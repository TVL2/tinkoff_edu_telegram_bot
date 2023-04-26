package ru.tinkoff.edu.java.scrapper.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tinkoff.edu.java.scrapper.entity.jpa.JpaChat;



public interface ChatRepository extends JpaRepository<JpaChat, Long> {
}
