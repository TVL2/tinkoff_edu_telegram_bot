package ru.tinkoff.edu.java.scrapper.repositories.jpa;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.entity.jpa.JpaChatofLink;

import java.util.List;

@Repository
@ConditionalOnProperty(prefix = "app", name = "database-access-type", havingValue = "jpa")
public interface ChatLinksRepository extends JpaRepository<JpaChatofLink, Long> {

    boolean existsByChatAndLinkId(Long id, Long id1);

    void deleteByChatAndLinkId(Long id, Long id1);

    boolean existsByLinkId(Long id);

    List<JpaChatofLink> findByChat(Long id);

    @Modifying
    @Query(value="SELECT chat FROM chat_links  WHERE link_id = :id",nativeQuery = true)
    List<JpaChatofLink> findAllLinkChats(@Param("id") Long id);
}
