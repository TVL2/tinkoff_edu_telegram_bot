package ru.tinkoff.edu.java.scrapper.repositories.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.entity.jpa.JpaChat;
import ru.tinkoff.edu.java.scrapper.repositories.IntegrationEnvironment;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaChatRepositoryTest extends IntegrationEnvironment {

    @Autowired
    ChatRepository chatRepository;


    @Test
    @Transactional
    @Rollback
    public void addChatTest() {
        assertThat(chatRepository.findAll()).isEmpty();
        chatRepository.save(new JpaChat(5L));
        assertEquals(1, chatRepository.findAll().size());
    }

    @Test
    @Transactional
    @Rollback
    public void removeChatTest() {
        chatRepository.save(new JpaChat(5L));
        assertEquals(1, chatRepository.findAll().size());
        chatRepository.deleteById(5L);
        assertThat(chatRepository.findAll()).isEmpty();
    }

    @Test
    @Transactional
    @Rollback
    public void findAllTest() {
        chatRepository.save(new JpaChat(5L));
        chatRepository.save(new JpaChat(7L));
        assertEquals(5, chatRepository.findAll().get(0).getId());
        assertEquals(7, chatRepository.findAll().get(1).getId());
    }






}
