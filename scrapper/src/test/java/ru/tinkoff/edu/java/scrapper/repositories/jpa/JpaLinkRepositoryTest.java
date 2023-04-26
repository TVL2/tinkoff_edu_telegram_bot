package ru.tinkoff.edu.java.scrapper.repositories.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.repositories.IntegrationEnvironment;

import java.sql.Timestamp;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaLinkRepositoryTest extends IntegrationEnvironment {

    @Autowired
    LinkRepository linkRepository;


    @Test
    @Transactional
    @Rollback
    void addLinkTest() {
        assertThat(linkRepository.findAll()).isEmpty();
        linkRepository.saveLink("link1",new Timestamp(0));
        assertEquals(1, linkRepository.findAll().size());
    }

    @Test
    @Transactional
    @Rollback
    void removeChatTest() {
        linkRepository.saveLink("link1",new Timestamp(0));
        assertEquals(1, linkRepository.findAll().size());
        Long id = linkRepository.findAll().get(0).getId();
        linkRepository.deleteById(id);
        assertThat(linkRepository.findAll()).isEmpty();
    }

    @Test
    @Transactional
    @Rollback
    void findAllTest() {
        linkRepository.saveLink("link1",new Timestamp(0));
        linkRepository.saveLink("link2",new Timestamp(0));
        assertEquals("link1", linkRepository.findAll().get(0).getLink());
        assertEquals("link2", linkRepository.findAll().get(1).getLink());
    }






}
