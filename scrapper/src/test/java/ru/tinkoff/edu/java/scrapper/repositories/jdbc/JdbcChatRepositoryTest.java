package ru.tinkoff.edu.java.scrapper.repositories.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.repositories.IntegrationEnvironment;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = JdbcChatRepository.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JdbcChatRepositoryTest extends IntegrationEnvironment {

    @Autowired
    JdbcChatRepository chatRepository;


    @Test
    @Transactional
    @Rollback
    void addChatTest() {
        assertThat(chatRepository.findAll()).isEmpty();
        chatRepository.addChat(5L);
        assertEquals(1, chatRepository.findAll().size());
    }

    @Test
    @Transactional
    @Rollback
    void removeChatTest() {
        chatRepository.addChat(5L);
        assertEquals(1, chatRepository.findAll().size());
        chatRepository.removeChat(5L);
        assertThat(chatRepository.findAll()).isEmpty();
    }

    @Test
    @Transactional
    @Rollback
    void findAllTest() {
        chatRepository.addChat(5L);
        chatRepository.addChat(7L);
        assertEquals(5, chatRepository.findAll().get(0).getId());
        assertEquals(7, chatRepository.findAll().get(1).getId());
    }






}
