package scrapper.repositories.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.repositories.jdbc.JdbcLinkRepository;
import scrapper.JdbcRepositoryEnvironment;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class JdbcLinkRepositoryTest extends JdbcRepositoryEnvironment {

    @Autowired
    JdbcLinkRepository linkRepository;


    @Test
    @Transactional
    @Rollback
    void addLinkTest() {
        assertThat(linkRepository.findAll()).isEmpty();
        linkRepository.addLink("link1");
        assertEquals(1, linkRepository.findAll().size());
    }

    @Test
    @Transactional
    @Rollback
    void removeChatTest() {
        linkRepository.addLink("link1");
        assertEquals(1, linkRepository.findAll().size());
        Long id = linkRepository.findAll().get(0).getId();
        linkRepository.removeLink(id);
        assertThat(linkRepository.findAll()).isEmpty();
    }

    @Test
    @Transactional
    @Rollback
    void findAllTest() {
        linkRepository.addLink("link1");
        linkRepository.addLink("link2");
        assertEquals("link1", linkRepository.findAll().get(0).getLink().toString());
        assertEquals("link2", linkRepository.findAll().get(1).getLink().toString());
    }






}
