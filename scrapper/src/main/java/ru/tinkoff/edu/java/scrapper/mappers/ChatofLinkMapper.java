package ru.tinkoff.edu.java.scrapper.mappers;


import org.springframework.jdbc.core.RowMapper;
import ru.tinkoff.edu.java.scrapper.entity.Chat;
import ru.tinkoff.edu.java.scrapper.entity.ChatofLink;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatofLinkMapper implements RowMapper<ChatofLink> {
    @Override
    public ChatofLink mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChatofLink chatofLink = new ChatofLink();
        chatofLink.setChat(rs.getLong("chat"));
        return chatofLink;
    }
}
