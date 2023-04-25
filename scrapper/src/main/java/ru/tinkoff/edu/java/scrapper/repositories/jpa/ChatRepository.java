package ru.tinkoff.edu.java.scrapper.repositories.jpa;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.entity.jpa.JpaChat;

@Repository
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public interface ChatRepository extends JpaRepository<JpaChat, Long> {
}
