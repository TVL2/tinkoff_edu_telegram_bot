package scrapper.repositories.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.entity.jpa.JpaChat;
import ru.tinkoff.edu.java.scrapper.entity.jpa.JpaChatofLink;
import ru.tinkoff.edu.java.scrapper.repositories.jpa.ChatLinksRepository;
import ru.tinkoff.edu.java.scrapper.repositories.jpa.ChatRepository;
import ru.tinkoff.edu.java.scrapper.repositories.jpa.LinkRepository;
import scrapper.JpaRepositoryEnvironment;

import java.sql.Timestamp;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class JpaChatLinksRepositoryTest extends JpaRepositoryEnvironment {

    @Autowired
    ChatLinksRepository chatLinksRepository;
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    LinkRepository linkRepository;


    @Test
    @Transactional
    @Rollback
    void addLinkTest() {
        assertThat(chatLinksRepository.findAll()).isEmpty();
        chatRepository.save(new JpaChat(2L));
        linkRepository.saveLink("link",new Timestamp(0));
        var linkId = linkRepository.findByUrl("link").getId();
        chatLinksRepository.save(new JpaChatofLink(2L, linkId));
        assertEquals(1, chatLinksRepository.findAll().size());
    }

    @Test
    @Transactional
    @Rollback
    void removeChatLinkTest() {
        chatRepository.save(new JpaChat(2L));
        linkRepository.saveLink("link",new Timestamp(0));
        var linkId = linkRepository.findByUrl("link").getId();
        chatLinksRepository.save(new JpaChatofLink(2L, linkId));
        assertEquals(1, chatLinksRepository.findAll().size());
        chatLinksRepository.deleteByChatAndLinkId(2L, linkId);
        assertThat(chatLinksRepository.findAll()).isEmpty();
    }

    @Test
    @Transactional
    @Rollback
    void findAllTest() {
        chatRepository.save(new JpaChat(2L));
        chatRepository.save(new JpaChat(3L));
        linkRepository.saveLink("link1",new Timestamp(0));
        linkRepository.saveLink("link2",new Timestamp(0));
        var linkId1 = linkRepository.findByUrl("link1").getId();
        chatLinksRepository.save(new JpaChatofLink(2L, linkId1));
        var linkId2 = linkRepository.findByUrl("link2").getId();
        chatLinksRepository.save(new JpaChatofLink(3L, linkId2));
        assertEquals(2, chatLinksRepository.findAll().size());
        assertEquals(2, chatLinksRepository.findAll().get(0).getChat());
        assertEquals(3, chatLinksRepository.findAll().get(1).getChat());
        assertEquals(linkId1, chatLinksRepository.findAll().get(0).getLinkId());
        assertEquals(linkId2, chatLinksRepository.findAll().get(1).getLinkId());
    }


}
