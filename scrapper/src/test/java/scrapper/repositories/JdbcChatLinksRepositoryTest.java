package scrapper.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.repositories.JdbcChatLinksRepository;
import ru.tinkoff.edu.java.scrapper.repositories.JdbcChatRepository;
import ru.tinkoff.edu.java.scrapper.repositories.JdbcLinkRepository;
import scrapper.JdbcRepositoryEnvironment;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class JdbcChatLinksRepositoryTest extends JdbcRepositoryEnvironment {

    @Autowired
    JdbcChatLinksRepository chatLinksRepository;
    @Autowired
    JdbcChatRepository chatRepository;
    @Autowired
    JdbcLinkRepository linkRepository;


    @Test
    @Transactional
    @Rollback
    void addLinkTest() {
        assertThat(chatLinksRepository.findAll()).isEmpty();
        chatRepository.addChat(2L);
        linkRepository.addLink("link");
        var linkId = linkRepository.getLink("link").getId();
        chatLinksRepository.addChatLink(2L, linkId);
        assertEquals(1, chatLinksRepository.findAll().size());
    }

    @Test
    @Transactional
    @Rollback
    void removeChatLinkTest() {
        chatRepository.addChat(2L);
        linkRepository.addLink("link");
        var linkId = linkRepository.getLink("link").getId();
        chatLinksRepository.addChatLink(2L, linkId);
        assertEquals(1, chatLinksRepository.findAll().size());
        chatLinksRepository.removeChatLink(2L, linkId);
        assertThat(chatLinksRepository.findAll()).isEmpty();
    }

    @Test
    @Transactional
    @Rollback
    void findAllTest() {
        chatRepository.addChat(2L);
        chatRepository.addChat(3L);
        linkRepository.addLink("link1");
        linkRepository.addLink("link2");
        var linkId1 = linkRepository.getLink("link1").getId();
        chatLinksRepository.addChatLink(2L, linkId1);
        var linkId2 = linkRepository.getLink("link2").getId();
        chatLinksRepository.addChatLink(3L, linkId2);
        assertEquals(2, chatLinksRepository.findAll().size());
        assertEquals(2, chatLinksRepository.findAll().get(0).getChat());
        assertEquals(3, chatLinksRepository.findAll().get(1).getChat());
        assertEquals(linkId1, chatLinksRepository.findAll().get(0).getLink_id());
        assertEquals(linkId2, chatLinksRepository.findAll().get(1).getLink_id());
    }


}
