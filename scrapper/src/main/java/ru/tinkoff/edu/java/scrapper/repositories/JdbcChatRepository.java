package ru.tinkoff.edu.java.scrapper.repositories;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.entity.Chat;
import ru.tinkoff.edu.java.scrapper.mappers.ChatMapper;
import ru.tinkoff.edu.java.scrapper.util.exceptions.ChatDoesNotExist;


import java.util.List;


@Repository
@AllArgsConstructor
public class JdbcChatRepository {

    private final JdbcTemplate jdbcTemplate;


    public void addChat(Long id) {
        jdbcTemplate.update("INSERT INTO chat(id) VALUES(?) ON CONFLICT (id) DO NOTHING", id);
    }

    public void removeChat(Long id) {
        if (jdbcTemplate.update("DELETE FROM chat WHERE id = ?", id) == 0) {
            throw new ChatDoesNotExist("Чат не существует!");
        }

    }

    public List<Chat> findAll() {
        return jdbcTemplate.query("SELECT * FROM chat", new ChatMapper());
    }

    public Boolean checkForAChat(Long id) {
        return jdbcTemplate.queryForObject("SELECT EXISTS (SELECT * FROM chat WHERE id = ?)", Boolean.class, id);
    }
}
