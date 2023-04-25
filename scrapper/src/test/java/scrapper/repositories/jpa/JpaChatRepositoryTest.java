package scrapper.repositories.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.entity.jpa.JpaChat;
import ru.tinkoff.edu.java.scrapper.repositories.jpa.ChatRepository;
import scrapper.JpaRepositoryEnvironment;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class JpaChatRepositoryTest extends JpaRepositoryEnvironment {

    @Autowired
    ChatRepository chatRepository;


    @Test
    @Transactional
    @Rollback
    void addChatTest() {
        assertThat(chatRepository.findAll()).isEmpty();
        chatRepository.save(new JpaChat(5L));
        assertEquals(1, chatRepository.findAll().size());
    }

    @Test
    @Transactional
    @Rollback
    void removeChatTest() {
        chatRepository.save(new JpaChat(5L));
        assertEquals(1, chatRepository.findAll().size());
        chatRepository.deleteById(5L);
        assertThat(chatRepository.findAll()).isEmpty();
    }

    @Test
    @Transactional
    @Rollback
    void findAllTest() {
        chatRepository.save(new JpaChat(5L));
        chatRepository.save(new JpaChat(7L));
        assertEquals(5, chatRepository.findAll().get(0).getId());
        assertEquals(7, chatRepository.findAll().get(1).getId());
    }






}
