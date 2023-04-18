package ru.tinkoff.edu.java.scrapper.repositories;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.entity.Link;
import ru.tinkoff.edu.java.scrapper.mappers.ChatMapper;
import ru.tinkoff.edu.java.scrapper.mappers.LinkMapper;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class JdbcChatLinksRepository {

    private final JdbcTemplate jdbcTemplate;

    public void addChatLink(Long chatId, Long linkId) {
        jdbcTemplate.update("INSERT INTO chat_links(chat, link_id) VALUES(?, ?)", chatId, linkId);
    }

    public void removeChatLink(Long chatId, Long linkId) {
        jdbcTemplate.update("DELETE FROM chat_links WHERE chat = ? AND link_id = ?", chatId, linkId);

    }

    public List<Link> findAllChatLinks(Long id) {
        return jdbcTemplate.queryForStream(
                "SELECT id, url, last_update FROM link JOIN chat_links cl on link.id = cl.link_id WHERE cl.chat = ?",
                new LinkMapper(), id).collect(Collectors.toList());
    }
    public Long[] findAllLinkChats(Long id) {
        return jdbcTemplate.queryForStream(
                "SELECT chat FROM chat_links  WHERE link_id = ?",
                new ChatMapper(), id).toArray(Long[]::new);
    }
}