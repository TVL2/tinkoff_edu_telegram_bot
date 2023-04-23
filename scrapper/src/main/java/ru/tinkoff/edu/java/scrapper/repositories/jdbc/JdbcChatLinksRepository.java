package ru.tinkoff.edu.java.scrapper.repositories.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.java.scrapper.entity.ChatforUpdate;
import ru.tinkoff.edu.java.scrapper.entity.ChatofLink;
import ru.tinkoff.edu.java.scrapper.entity.Link;
import ru.tinkoff.edu.java.scrapper.mappers.ChatforUpdateMapper;
import ru.tinkoff.edu.java.scrapper.mappers.ChatofLinkMapper;
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
    public List<ChatofLink> findAll() {
        return jdbcTemplate.query("SELECT * FROM chat_links", new ChatofLinkMapper());
    }


    public List<Link> findAllChatLinks(Long id) {
        return jdbcTemplate.queryForStream(
                "SELECT id, url, last_update FROM link JOIN chat_links cl on link.id = cl.link_id WHERE cl.chat = ?",
                new LinkMapper(), id).collect(Collectors.toList());
    }

    public Long[] findAllLinkChats(Long id) {
        return jdbcTemplate.queryForStream(
                "SELECT chat FROM chat_links  WHERE link_id = ?",
                new ChatforUpdateMapper(), id).map(ChatforUpdate::getChat).toArray(Long[]::new);
    }

    public Boolean findChatAndLink(Long chatId , Long linkId) {
        return jdbcTemplate.queryForObject(
                "SELECT EXISTS (SELECT * FROM chat_links WHERE chat = ? AND link_id = ?)",
                Boolean.class, chatId, linkId);
    }

}
