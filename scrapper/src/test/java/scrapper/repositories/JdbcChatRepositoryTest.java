package scrapper.repositories;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.entity.Chat;
import ru.tinkoff.edu.java.scrapper.mappers.ChatMapper;

import java.util.List;


@Repository
@AllArgsConstructor
public class JdbcChatRepositoryTest {

    private final JdbcTemplate jdbcTemplate;


    public void addChat(Long id) {
        jdbcTemplate.update("INSERT INTO chat(id) VALUES(?)", id);
    }

    public void removeChat(Long id) {
            jdbcTemplate.update("DELETE FROM chat WHERE id = ?", id);

    }

    public List<Chat> findAll() {
        return jdbcTemplate.query("SELECT * FROM chat", new ChatMapper());
    }
}
