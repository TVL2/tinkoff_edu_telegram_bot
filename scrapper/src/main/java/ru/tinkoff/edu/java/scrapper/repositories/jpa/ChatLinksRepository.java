package ru.tinkoff.edu.java.scrapper.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.tinkoff.edu.java.scrapper.entity.jpa.JpaChatofLink;

import java.util.List;



public interface ChatLinksRepository extends JpaRepository<JpaChatofLink, Long> {

    boolean existsByChatAndLinkId(Long id, Long id1);

    void deleteByChatAndLinkId(Long id, Long id1);

    boolean existsByLinkId(Long id);

    List<JpaChatofLink> findByChat(Long id);

    @Modifying
    @Query(value="SELECT chat FROM chat_links  WHERE link_id = :id",nativeQuery = true)
    List<JpaChatofLink> findAllLinkChats(@Param("id") Long id);
}
